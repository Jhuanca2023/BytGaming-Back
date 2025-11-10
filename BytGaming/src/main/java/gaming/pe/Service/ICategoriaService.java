package gaming.pe.Service;


import gaming.pe.DTO.CategoryDTO;
import gaming.pe.Entity.Category;


import java.util.List;
import java.util.Optional;

public interface ICategoriaService {
    List<Category> FindAll();
    Optional<Category> FindById(Long id);
    Category create(CategoryDTO categoriaDTO);
    Category update(Long id,CategoryDTO dto);
    void delete(Long id);
}
