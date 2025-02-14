package stay.space.startup.service;

import stay.space.startup.persistence.entity.PropertyImage;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing property images.
 */
public interface PropertyImageService {

    /**
     * Retrieves all property images.
     *
     * @return a list of property images
     */
    List<PropertyImage> getAllPropertyImages();

    /**
     * Retrieves a property image by its ID.
     *
     * @param id the ID of the property image to retrieve
     * @return an optional containing the found property image or empty if no property image was found
     */
    Optional<PropertyImage> getPropertyImageById(Long id);

    /**
     * Creates a new property image.
     *
     * @param propertyImage the property image to create
     * @return the created property image
     */
    PropertyImage createPropertyImage(PropertyImage propertyImage);

    /**
     * Deletes a property image by its ID.
     *
     * @param id the ID of the property image to delete
     * @return true if the property image was deleted, false if the property image was not found
     */
    boolean deletePropertyImage(Long id);

    /**
     * Retrieves property images by property ID.
     *
     * @param propertyId the ID of the property the images are associated with
     * @return a list of images associated with the specified property
     */
    List<PropertyImage> findPropertyImagesByPropertyId(Long propertyId);
}

