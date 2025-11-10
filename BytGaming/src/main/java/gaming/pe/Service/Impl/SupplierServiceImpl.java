package gaming.pe.Service.Impl;

import gaming.pe.DTO.SupplierDTO;
import gaming.pe.Entity.Supplier;
import gaming.pe.Mappers.SupplierMapper;
import gaming.pe.Repository.SupplierRepository;
import gaming.pe.Service.SupplierService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper mapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, SupplierMapper mapper) {
        this.supplierRepository = supplierRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier findById(Long id) {
        return supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Supplier not found"));
    }

    @Override
    public SupplierDTO save(SupplierDTO supplierDTO) {
        Supplier supplier = mapper.toEntity(supplierDTO);
        return mapper.toDTO(supplierRepository.save(supplier));
    }

    @Override
    public SupplierDTO update(Long id, SupplierDTO dto) {
        Supplier existing = findById(id);
        mapper.updateSupplierFromDto(dto, existing);
        Supplier updated = supplierRepository.save(existing);
        return mapper.toDTO(updated);
    }
    @Override
    public void delete(Long id) {
        supplierRepository.deleteById(id);
    }

}
