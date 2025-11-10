package gaming.pe.Service;

import gaming.pe.DTO.UserAddressDTO;

import java.util.List;

public interface UserAddressService {
    List<UserAddressDTO> findAllByUser(Long userId);
    UserAddressDTO findById(Long id);
    UserAddressDTO create(UserAddressDTO dto, Long userId);
    UserAddressDTO update(Long id, UserAddressDTO dto);
    void delete(Long id);
}
