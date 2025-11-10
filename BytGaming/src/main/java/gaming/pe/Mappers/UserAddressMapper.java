package gaming.pe.Mappers;

import gaming.pe.DTO.UserAddressDTO;
import gaming.pe.Entity.UserAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserAddressMapper {

    UserAddressDTO toDto(UserAddress entity);

    UserAddress toEntity(UserAddressDTO dto);

    void updateFromDto(UserAddressDTO dto, @MappingTarget UserAddress entity);
}