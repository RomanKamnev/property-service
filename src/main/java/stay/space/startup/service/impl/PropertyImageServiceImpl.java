package stay.space.startup.service.impl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import stay.space.startup.persistence.entity.PropertyImage;
import stay.space.startup.persistence.repository.PropertyImageRepository;

import java.util.List;
import java.util.Optional;

@Service
@Getter @Setter
public class PropertyImageServiceImpl {

    private final PropertyImageRepository propertyImageRepository;

    public PropertyImageServiceImpl(PropertyImageRepository propertyImageRepository) {
        this.propertyImageRepository = propertyImageRepository;
    }

    public List<PropertyImage> getAllPropertyImages() {
        return propertyImageRepository.findAll();
    }

    public Optional<PropertyImage> getPropertyImageById(Long id) {
        return propertyImageRepository.findById(id);
    }

    public PropertyImage createPropertyImage(PropertyImage propertyImage) {
        return propertyImageRepository.save(propertyImage);
    }

    public boolean deletePropertyImage(Long id) {
        return propertyImageRepository.findById(id)
                .map(image -> {
                    propertyImageRepository.delete(image);
                    return true;
                })
                .orElse(false);
    }

    public List<PropertyImage> findPropertyImagesByPropertyId(Long propertyId) {
        return propertyImageRepository.findByPropertyId(propertyId);
    }
}

