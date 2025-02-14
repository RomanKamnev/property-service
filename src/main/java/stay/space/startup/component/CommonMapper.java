package stay.space.startup.component;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class CommonMapper {
    public CommonMapper() {
    }

    public Date mapLongToDate(Long value) {
        return value == null ? null : new Date(value);
    }

    public Long mapDateToLong(Date date) {
        return date == null ? null : date.getTime();
    }

    public Long mapLocalDateTimeToLong(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
