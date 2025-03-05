package stay.space.startup.messaging;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    private String propertyName;
    private String address;
    @Nullable
    private LocalDateTime timestamp;

    @Override
    public String toString() {
        return "Message{" +
                "propertyName='" + propertyName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
