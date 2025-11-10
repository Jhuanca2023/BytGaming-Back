package gaming.pe.Mappers;


import gaming.pe.DTO.KardexDTO;
import gaming.pe.Entity.Kardex;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface KardexMapper {
    KardexDTO toDto(Kardex kardex);
    Kardex toEntity(KardexDTO kardexDTO);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(KardexDTO dto, @MappingTarget Kardex entity);
}
