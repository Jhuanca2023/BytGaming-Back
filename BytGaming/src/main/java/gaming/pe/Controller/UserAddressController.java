package gaming.pe.Controller;
import gaming.pe.DTO.UserAddressDTO;
import gaming.pe.Entity.UserEntity;
import gaming.pe.Repository.UsersRepository;
import gaming.pe.Service.UserAddressService;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class UserAddressController {

    private final UserAddressService service;
    private final UsersRepository userRepository;

    @GetMapping
    public ResponseEntity<List<UserAddressDTO>> getMyAddresses(Authentication authentication) {
        String email = authentication.getName(); // obtiene el email del usuario autenticado

        UserEntity user = userRepository.findUserEntitiesByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        List<UserAddressDTO> list = service.findAllByUser(user.getId());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAddressDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserAddressDTO> create(Authentication authentication, @RequestBody UserAddressDTO dto) {
        String email = authentication.getName(); // obtiene el email del usuario autenticado

        UserEntity user = userRepository.findUserEntitiesByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return ResponseEntity.ok(service.create(dto, user.getId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserAddressDTO> update(@PathVariable Long id,
                                                 @RequestBody UserAddressDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
