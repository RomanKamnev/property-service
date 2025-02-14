package stay.space.startup.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stay.space.startup.persistence.entity.Property;
import stay.space.startup.service.impl.PropertyServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/properties")
@Api(value = "Property Management System", tags = {"Properties"})
public class PropertyController {

    private final PropertyServiceImpl propertyServiceImpl;

    @Autowired
    public PropertyController(PropertyServiceImpl propertyServiceImpl) {
        this.propertyServiceImpl = propertyServiceImpl;
    }

    @GetMapping
    @ApiOperation(value = "View a list of available properties", response = List.class)
    public List<Property> getAllProperties() {
        return propertyServiceImpl.getAllProperties();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a property by ID", response = Property.class)
    public ResponseEntity<Property> getPropertyById(
            @ApiParam(value = "ID of the property to retrieve", required = true)
            @PathVariable Long id) {
        Optional<Property> property = propertyServiceImpl.getPropertyById(id);
        return property.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ApiOperation(value = "Add a new property")
    public Property createProperty(
            @ApiParam(value = "Property object to store in the database", required = true)
            @RequestBody Property property) {
        return propertyServiceImpl.createProperty(property);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing property")
    public ResponseEntity<Property> updateProperty(
            @ApiParam(value = "ID of the property to update", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Updated property object", required = true)
            @RequestBody Property propertyDetails) {
        Optional<Property> updatedProperty = propertyServiceImpl.updateProperty(id, propertyDetails);
        return updatedProperty.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a property")
    public ResponseEntity<Void> deleteProperty(
            @ApiParam(value = "ID of the property to delete", required = true)
            @PathVariable Long id) {
        boolean isDeleted = propertyServiceImpl.deleteProperty(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

