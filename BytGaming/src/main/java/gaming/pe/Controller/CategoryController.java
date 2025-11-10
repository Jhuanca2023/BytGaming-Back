package gaming.pe.Controller;

import gaming.pe.DTO.CategoryDTO;
import gaming.pe.Entity.Category;
import gaming.pe.Service.ICategoriaService;
import gaming.pe.Service.Impl.CategoriaService;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoriaService.FindAll();
        return ResponseEntity.ok(categories);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> optional = categoriaService.FindById(id);
        return optional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva categoría
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryDTO) {
        try {
            Category createdCategory = categoriaService.create(categoryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Actualizar una categoría existente
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        try {
            Category updatedCategory = categoriaService.update(id, categoryDTO);
            return ResponseEntity.ok(updatedCategory);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una categoría por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        try {
            categoriaService.delete(id);  // Llamamos al service para eliminarla
            return ResponseEntity.noContent().build();  // Si todo va bien, retornamos NO_CONTENT
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // Si no la encontramos, retornamos NOT_FOUND
        }
    }
}