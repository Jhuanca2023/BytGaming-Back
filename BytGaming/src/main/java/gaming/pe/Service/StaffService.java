package gaming.pe.Service;

import gaming.pe.Entity.Staff;

import java.util.List;
import java.util.Optional;

public interface StaffService {
    List<Staff> findAll();

    Optional<Staff> findById(Long id);

    Staff save(Staff staff);

    Staff update(Long id, Staff staff);

    void delete(Long id);
}
