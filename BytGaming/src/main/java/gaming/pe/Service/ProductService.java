package gaming.pe.Service;

import gaming.pe.DTO.ProductCreateDTO;
import gaming.pe.Entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product save(ProductCreateDTO dto, MultipartFile file) throws IOException;

    Product updateImage(Long productId, MultipartFile file) throws IOException;

    List<Product> findAll();

    Optional<Product> findById(Long id);

    void delete(Product book) throws IOException;

    Product update(Long id, ProductCreateDTO dto, MultipartFile file)throws IOException;
}
