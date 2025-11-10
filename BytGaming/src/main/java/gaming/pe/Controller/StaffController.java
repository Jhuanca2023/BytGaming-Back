package gaming.pe.Controller;

import gaming.pe.Entity.Staff;
import gaming.pe.Service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    // Obtener todos los Staff
    @GetMapping
    public ResponseEntity<List<Staff>> getAllStaff() {
        List<Staff> staffList = staffService.findAll();
        return new ResponseEntity<>(staffList, HttpStatus.OK);
    }

    // Obtener Staff por ID
    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable Long id) {
        Optional<Staff> staff = staffService.findById(id);
        return staff.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear un nuevo Staff
    @PostMapping
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) {
        Staff createdStaff = staffService.save(staff);
        return new ResponseEntity<>(createdStaff, HttpStatus.CREATED);
    }

    // Actualizar Staff
    @PutMapping("/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable Long id, @RequestBody Staff staff) {
        Staff updatedStaff = staffService.update(id, staff);
        return updatedStaff != null ? ResponseEntity.ok(updatedStaff) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Eliminar Staff
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        staffService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
