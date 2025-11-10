package gaming.pe.Service.Impl;

import gaming.pe.DTO.KardexDTO;
import gaming.pe.Entity.Kardex;
import gaming.pe.Entity.Product;
import gaming.pe.Entity.Staff;
import gaming.pe.Entity.Supplier;
import gaming.pe.Enums.MovementType;
import gaming.pe.Mappers.KardexMapper;
import gaming.pe.Repository.KardexRepository;
import gaming.pe.Repository.ProductRepository;
import gaming.pe.Repository.StaffRepository;
import gaming.pe.Repository.SupplierRepository;
import gaming.pe.Service.IKardexService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class KardexServiceImpl implements IKardexService {

    private final KardexRepository kardexRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final StaffRepository staffRepository;
    private final KardexMapper kardexMapper;



    @Override
    public List<Kardex> findAll() {
        return kardexRepository.findAll();
    }

    @Override
    public Optional<Kardex> FindById(Long id) {
        return kardexRepository.findById(id);
    }

    @Override
    public KardexDTO create(KardexDTO dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        Integer currentStock = Optional.ofNullable(product.getUnits()).orElse(0);
        Integer qty = dto.getQuantity();
        Integer newStock;
        switch (dto.getMovementType().toUpperCase()) {
            case "ENTRADA":
                newStock = currentStock + qty;
                break;
            case "SALIDA":
                newStock = currentStock - qty;
                if (newStock < 0) {
                    throw new RuntimeException("Stock insuficiente. Stock actual: " + currentStock);
                }
                break;
            default:
                throw new RuntimeException("Tipo de movimiento inválido: " + dto.getMovementType());
        }
        product.setUnits(newStock);
        productRepository.save(product);

        Kardex kardex = kardexMapper.toEntity(dto);
        kardex.setProduct(getProductById(dto.getProductId()));
        kardex.setProvider(getSupplierById(dto.getSupplierId()));
        kardex.setStaff(getStaffById(dto.getStaffId()));

        Kardex saved = kardexRepository.save(kardex);
        return kardexMapper.toDto(saved);
    }

    @Transactional
    public KardexDTO update(Long id, KardexDTO dto) {
        Kardex existing = kardexRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kardex no encontrado"));

        Product product = existing.getProduct();
        Integer stock = Optional.ofNullable(product.getUnits()).orElse(0);

        // 1. Obtenemos los valores previos del Kardex existente
        MovementType oldType = existing.getMovementType();
        Integer oldQty = Optional.ofNullable(existing.getQuantity()).orElse(0);

        // 2. Revertimos el movimiento anterior
        if (oldType == MovementType.ENTRADA) {
            stock -= oldQty;
        } else if (oldType == MovementType.SALIDA) {
            stock += oldQty;
        }

        // 3. Determinamos el nuevo tipo de movimiento y cantidad
        // Si el DTO tiene el movimiento como String, lo convertimos a MovementType
        MovementType newType = null;
        if (dto.getMovementType() != null) {
            try {
                newType = MovementType.valueOf(dto.getMovementType().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Tipo de movimiento inválido: " + dto.getMovementType());
            }
        } else {
            newType = oldType; // Si el movimiento no viene, usamos el anterior
        }

        Integer newQty = (dto.getQuantity() != null) ? dto.getQuantity() : oldQty;

        // 4. Aplicamos el nuevo movimiento
        if (newType == MovementType.ENTRADA) {
            stock += newQty;
        } else if (newType == MovementType.SALIDA) {
            if (stock < newQty) {
                throw new RuntimeException("Stock insuficiente. Stock actual: " + stock);
            }
            stock -= newQty;
        } else {
            throw new RuntimeException("Tipo de movimiento inválido: " + newType);
        }

        // 5. Actualizamos el stock del producto
        product.setUnits(stock);
        productRepository.save(product);

        // 6. Actualizamos los valores del Kardex con los datos del DTO
        kardexMapper.updateFromDto(dto, existing);
        existing.setProduct(product);

        // 7. Establecemos el proveedor y el staff si están presentes
        if (dto.getSupplierId() != null) {
            existing.setProvider(getSupplierById(dto.getSupplierId()));
        }
        if (dto.getStaffId() != null) {
            existing.setStaff(getStaffById(dto.getStaffId()));
        }

        // 8. Guardamos el Kardex actualizado
        Kardex updated = kardexRepository.save(existing);

        return kardexMapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        Kardex kardex = kardexRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kardex no encontrado con ID: " + id));

        kardexRepository.delete(kardex);
    }
    private Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    private Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
    }

    private Staff getStaffById(Long id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff no encontrado"));
    }
}
