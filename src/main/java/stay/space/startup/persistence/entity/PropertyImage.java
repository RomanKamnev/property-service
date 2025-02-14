package stay.space.startup.persistence.entity;

import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "property_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The unique ID of the property image")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    @ApiModelProperty(notes = "The property associated with the image")
    private Property property;

    @Column(nullable = false, length = 255)
    @ApiModelProperty(notes = "The URL of the property image")
    private String imageUrl;

    @Column(nullable = false)
    @ApiModelProperty(notes = "The date and time when the property image was created")
    private LocalDateTime createdAt;
}
