package gaming.pe.Service.Impl;

import gaming.pe.DTO.CategoryDTO;
import gaming.pe.Entity.Category;
import gaming.pe.Mappers.CategoryMapper;
import gaming.pe.Repository.CategoryRepository;
import gaming.pe.Service.ICategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService implements ICategoriaService {


    private final CategoryRepository repository;
    private final CategoryMapper categoryMapper;



    @Override
    public List<Category> FindAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Category> FindById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Category create(CategoryDTO categoriaDTO) {
        Category category = categoryMapper.toEntity(categoriaDTO);

        return repository.save(category);
    }

    @Override
    public Category update(Long id,CategoryDTO dto) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));

        categoryMapper.updateFromDto(dto, category);

        return repository.save(category);
    }

    @Override
    public void delete(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));

        repository.delete(category);
    }
}