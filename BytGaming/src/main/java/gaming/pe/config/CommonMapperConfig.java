package gaming.pe.config;

import org.mapstruct.*;

@MapperConfig(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG
)
public interface CommonMapperConfig {
}