package gaming.pe.Service;

import gaming.pe.DTO.KardexDTO;
import gaming.pe.Entity.Kardex;

import java.util.List;
import java.util.Optional;

public interface IKardexService {
    List<Kardex> findAll();
    Optional<Kardex> FindById(Long id);
    KardexDTO create(KardexDTO dto);
    KardexDTO update(Long id,KardexDTO dto);
    void delete(Long id);
}
