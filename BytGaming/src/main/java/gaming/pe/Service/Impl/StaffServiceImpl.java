package gaming.pe.Service.Impl;

import gaming.pe.Entity.Staff;
import gaming.pe.Repository.StaffRepository;
import gaming.pe.Service.StaffService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public Optional<Staff> findById(Long id) {
        return staffRepository.findById(id);
    }

    @Override
    public Staff save(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public Staff update(Long id, Staff staff) {
        if (staffRepository.existsById(id)) {
            // Encontramos el Staff existente
            Staff existingStaff = staffRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Staff no encontrado"));

            // Actualizamos solo los campos que están presentes en el objeto staff recibido
            if (staff.getName() != null) {
                existingStaff.setName(staff.getName());
            }
            if (staff.getEmail() != null) {
                existingStaff.setEmail(staff.getEmail());
            }
            if (staff.getPost() != null) {
                existingStaff.setPost(staff.getPost());
            }
            // Aquí puedes agregar más campos si es necesario

            // Guardamos los cambios
            return staffRepository.save(existingStaff);
        }
        return null; // O lanzar una excepción, dependiendo del caso
    }

    @Override
    public void delete(Long id) {
        if (staffRepository.existsById(id)) {
            staffRepository.deleteById(id);
        }
    }
}
