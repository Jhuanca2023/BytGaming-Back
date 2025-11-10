package gaming.pe.DTO.Auth;

import jakarta.validation.constraints.Size;

public record AuthResponseRegisterDto(
        @Size(max = 3, message = "The user cannot have more than 3 roles") String roleListName){
        }