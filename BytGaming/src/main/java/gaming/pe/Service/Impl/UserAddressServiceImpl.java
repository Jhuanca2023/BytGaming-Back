package gaming.pe.Service.Impl;


import gaming.pe.DTO.UserAddressDTO;
import gaming.pe.Entity.UserAddress;
import gaming.pe.Entity.UserEntity;
import gaming.pe.Mappers.UserAddressMapper;
import gaming.pe.Repository.UserAddressRepository;
import gaming.pe.Repository.UsersRepository;
import gaming.pe.Service.UserAddressService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAddressServiceImpl implements UserAddressService {

    private final UserAddressRepository addressRepo;
    private final UsersRepository userRepo;
    private final UserAddressMapper mapper;

    @Override
    public List<UserAddressDTO> findAllByUser(Long userId) {
        return addressRepo.findByUserId(userId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserAddressDTO findById(Long id) {
        UserAddress ua = addressRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Address no encontrada"));
        return mapper.toDto(ua);
    }

    @Override
    @Transactional
    public UserAddressDTO create(UserAddressDTO dto, Long userId) {
        UserEntity user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        UserAddress entity = mapper.toEntity(dto);
        entity.setUser(user);
        UserAddress saved = addressRepo.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public UserAddressDTO update(Long id, UserAddressDTO dto) {
        UserAddress existing = addressRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Address no encontrada"));
        mapper.updateFromDto(dto, existing);
        UserAddress updated = addressRepo.save(existing);
        return mapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        if (!addressRepo.existsById(id)) {
            throw new RuntimeException("Address no encontrada");
        }
        addressRepo.deleteById(id);
    }
}

