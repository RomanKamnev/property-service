package stay.space.startup.service.impl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import stay.space.startup.persistence.entity.Property;
import stay.space.startup.persistence.repository.PropertyRepository;

import java.util.List;
import java.util.Optional;

@Service
@Getter @Setter
public class PropertyServiceImpl {

    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Cacheable(value = "getAllProperties")
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Optional<Property> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    public Optional<Property> updateProperty(Long id, Property propertyDetails) {
        return propertyRepository.findById(id)
                .map(property -> {
                    property.setTitle(propertyDetails.getTitle());
                    property.setDescription(propertyDetails.getDescription());
                    property.setAddress(propertyDetails.getAddress());
                    property.setCity(propertyDetails.getCity());
                    property.setState(propertyDetails.getState());
                    property.setCountry(propertyDetails.getCountry());
                    property.setPricePerNight(propertyDetails.getPricePerNight());
                    property.setMaxGuests(propertyDetails.getMaxGuests());
                    property.setUpdatedAt(propertyDetails.getUpdatedAt());
                    return propertyRepository.save(property);
                });
    }

    public boolean deleteProperty(Long id) {
        return propertyRepository.findById(id)
                .map(property -> {
                    propertyRepository.delete(property);
                    return true;
                })
                .orElse(false);
    }

    public List<Property> findPropertiesByOwnerId(Long ownerId) {
        return propertyRepository.findByOwnerId(ownerId);
    }

    public List<Property> findPropertiesByCity(String city) {
        return propertyRepository.findByCity(city);
    }

    public List<Property> findPropertiesByCountry(String country) {
        return propertyRepository.findByCountry(country);
    }
}

