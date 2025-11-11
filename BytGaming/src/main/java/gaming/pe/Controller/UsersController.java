package gaming.pe.Controller;


import gaming.pe.DTO.Auth.AuthCreateUserRequestDto;
import gaming.pe.DTO.Auth.AuthLoginRequestDto;
import gaming.pe.DTO.Auth.AuthResponseDto;
import gaming.pe.DTO.Auth.AuthUpdateUserRequestDto;
import gaming.pe.Entity.UserEntity;
import gaming.pe.Service.Impl.UserDetailServiceImpl;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/auth")
public class UsersController {
    @Autowired
    private UserDetailServiceImpl userDetailService;

    @PostMapping("/sign")
    public ResponseEntity<AuthResponseDto> register(@RequestBody @Valid AuthCreateUserRequestDto userRequest) throws BadRequestException {
        return new ResponseEntity<>(this.userDetailService.createUser(userRequest), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AuthResponseDto> updateUser(@PathVariable Long id, @RequestBody AuthUpdateUserRequestDto request) {
        try {
            // Llamamos al servicio para actualizar el usuario
            AuthResponseDto updatedUser = userDetailService.updateUser(id, request);
            return ResponseEntity.ok(updatedUser); // Retorna una respuesta exitosa
        } catch (Exception e) {
            // Si ocurre un error, devolvemos una respuesta de error
            return ResponseEntity.badRequest().body(new AuthResponseDto(null, e.getMessage(), null, false));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody @Valid AuthLoginRequestDto userRequest){
        try {
            AuthResponseDto response = this.userDetailService.loginUser(userRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (org.springframework.security.authentication.BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponseDto(null, "Credenciales inválidas", null, false));
        } catch (org.springframework.security.core.userdetails.UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponseDto(null, "Usuario no encontrado", null, false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthResponseDto(null, "Error al iniciar sesión: " + e.getMessage(), null, false));
        }
    }

    @GetMapping
   // @PreAuthorize("hasRole('ADMIN')")Z
    public List<UserEntity> listUsers() {
        return userDetailService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@PathVariable Long id) {
        userDetailService.deleteUser(id);
        return "Usuario eliminado correctamente";
    }
}
