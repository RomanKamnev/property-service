package stay.space.startup.service;

import stay.space.startup.persistence.entity.Property;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing properties.
 */
public interface PropertyService {

    /**
     * Retrieves all properties.
     *
     * @return a list of properties
     */
    List<Property> getAllProperties();

    /**
     * Retrieves a property by its ID.
     *
     * @param id the ID of the property to retrieve
     * @return an optional containing the found property or empty if no property was found
     */
    Optional<Property> getPropertyById(Long id);

    /**
     * Creates a new property.
     *
     * @param property the property to create
     * @return the created property
     */
    Property createProperty(Property property);

    /**
     * Updates an existing property.
     *
     * @param id the ID of the property to update
     * @param propertyDetails the new details of the property
     * @return an optional containing the updated property or empty if no property was found
     */
    Optional<Property> updateProperty(Long id, Property propertyDetails);

    /**
     * Deletes a property by its ID.
     *
     * @param id the ID of the property to delete
     * @return true if the property was deleted, false if the property was not found
     */
    boolean deleteProperty(Long id);

    /**
     * Retrieves properties by the owner's ID.
     *
     * @param ownerId the ID of the property owner
     * @return a list of properties owned by the specified user
     */
    List<Property> findPropertiesByOwnerId(Long ownerId);

    /**
     * Retrieves properties by city.
     *
     * @param city the city to filter properties by
     * @return a list of properties in the specified city
     */
    List<Property> findPropertiesByCity(String city);

    /**
     * Retrieves properties by country.
     *
     * @param country the country to filter properties by
     * @return a list of properties in the specified country
     */
    List<Property> findPropertiesByCountry(String country);
}

