package stay.space.startup.web;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import stay.space.startup.persistence.entity.PropertyImage;
import stay.space.startup.service.impl.PropertyImageServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/property-images")
@Api(value = "Property Image Management System", tags = {"Property Images"})
public class PropertyImageController {

    private final PropertyImageServiceImpl propertyImageServiceImpl;

    @Autowired
    public PropertyImageController(PropertyImageServiceImpl propertyImageServiceImpl) {
        this.propertyImageServiceImpl = propertyImageServiceImpl;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "View a list of all property images", response = List.class)
    public List<PropertyImage> getAllPropertyImages() {
        return propertyImageServiceImpl.getAllPropertyImages();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "Get a property image by ID", response = PropertyImage.class)
    public ResponseEntity<PropertyImage> getPropertyImageById(
            @ApiParam(value = "ID of the property image to retrieve", required = true)
            @PathVariable Long id) {
        Optional<PropertyImage> propertyImage = propertyImageServiceImpl.getPropertyImageById(id);
        return propertyImage.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ApiOperation(value = "Upload a new property image")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public PropertyImage createPropertyImage(
            @ApiParam(value = "Property image object to store in the database", required = true)
            @RequestBody PropertyImage propertyImage) {
        return propertyImageServiceImpl.createPropertyImage(propertyImage);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a property image")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deletePropertyImage(
            @ApiParam(value = "ID of the property image to delete", required = true)
            @PathVariable Long id) {
        boolean isDeleted = propertyImageServiceImpl.deletePropertyImage(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
