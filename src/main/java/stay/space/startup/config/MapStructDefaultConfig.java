package stay.space.startup.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;
import stay.space.startup.component.CommonMapper;

@MapperConfig(
        uses = {CommonMapper.class},
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        componentModel = "spring"
)
public class MapStructDefaultConfig {
}
