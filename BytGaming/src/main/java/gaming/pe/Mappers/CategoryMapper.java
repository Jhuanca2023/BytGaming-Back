package gaming.pe.Mappers;

import gaming.pe.DTO.CategoryDTO;
import gaming.pe.Entity.Category;
import gaming.pe.config.CommonMapperConfig;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDto(Category category);
    Category toEntity(CategoryDTO category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(CategoryDTO dto, @MappingTarget Category entity);
}
