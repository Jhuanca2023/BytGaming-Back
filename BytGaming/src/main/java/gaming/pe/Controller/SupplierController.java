package gaming.pe.Controller;

import gaming.pe.DTO.SupplierDTO;
import gaming.pe.Entity.Supplier;
import gaming.pe.Service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService service;

    public SupplierController(SupplierService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Supplier>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<SupplierDTO> save(@RequestBody SupplierDTO dto) {
        SupplierDTO created = service.save(dto);
        return ResponseEntity.status(201).body(created); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> update(@PathVariable Long id, @RequestBody SupplierDTO dto) {
        SupplierDTO updatedSupplier = service.update(id, dto);
        return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
