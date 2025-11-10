package gaming.pe.Mappers;

import gaming.pe.DTO.ProductCreateDTO;
import gaming.pe.Entity.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductCreateDTO toDto(Product product);

    Product toEntity(ProductCreateDTO productCreateDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(ProductCreateDTO dto, @MappingTarget Product entity);

}
