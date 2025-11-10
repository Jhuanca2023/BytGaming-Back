package gaming.pe.Service.Impl;

import gaming.pe.DTO.ProductCreateDTO;
import gaming.pe.Entity.Category;
import gaming.pe.Entity.Image;
import gaming.pe.Entity.Product;
import gaming.pe.Mappers.ProductMapper;
import gaming.pe.Repository.CategoryRepository;
import gaming.pe.Repository.ProductRepository;
import gaming.pe.Service.ImageService;
import gaming.pe.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ImageService imageService;
    private final ProductMapper productMapper;

    @Override
    public Product save(ProductCreateDTO dto, MultipartFile file) throws IOException {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Image image = imageService.uploadImage(file);
        Product product = productMapper.toEntity(dto);

        product.setCategory(category);
        product.setImage(image);
        return  productRepository.save(product);
    }

    @Override
    public Product updateImage(Long productId, MultipartFile file) throws IOException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        imageService.deleteImage(product.getImage());

        Image newImage = imageService.uploadImage(file);
        product.setImage(newImage);

        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void delete(Product product) throws IOException {
        if(product.getImage() != null) {
            imageService.deleteImage(product.getImage());
        }
        productRepository.deleteById(product.getId());
    }

    @Override
    public Product update(Long id, ProductCreateDTO dto, MultipartFile file) throws IOException {

        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id " + id));


        productMapper.updateFromDto(dto, existing);


        if (file != null && !file.isEmpty()) {
            if (existing.getImage() != null) {
                imageService.deleteImage(existing.getImage());
            }
            Image newImage = imageService.uploadImage(file);
            existing.setImage(newImage);
        }

        // 4. Actualizar categoría si el ID fue proporcionado
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id " + dto.getCategoryId()));
            existing.setCategory(category);
        }

        // 5. Guardar cambios en la base de datos
        return productRepository.save(existing);
    }
    }