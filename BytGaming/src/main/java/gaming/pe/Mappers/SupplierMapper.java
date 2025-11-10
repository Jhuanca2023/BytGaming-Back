package gaming.pe.Mappers;

import gaming.pe.DTO.SupplierDTO;
import gaming.pe.Entity.Supplier;
import gaming.pe.config.CommonMapperConfig;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(config = CommonMapperConfig.class)
public interface SupplierMapper {
    SupplierDTO toDTO(Supplier entity);
    Supplier toEntity(SupplierDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSupplierFromDto(SupplierDTO dto, @MappingTarget Supplier entity);
}
