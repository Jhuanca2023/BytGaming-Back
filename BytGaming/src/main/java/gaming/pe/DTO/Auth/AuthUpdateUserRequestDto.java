package gaming.pe.DTO.Auth;

import gaming.pe.Enums.RoleEnum;

public record AuthUpdateUserRequestDto(
        String email,
        String password,
        String name,
        String lastName,
        RoleEnum role // Si deseas incluir la opción de actualizar el rol
)
{

}