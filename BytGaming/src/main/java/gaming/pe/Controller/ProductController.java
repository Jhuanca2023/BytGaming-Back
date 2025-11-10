package gaming.pe.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gaming.pe.DTO.ProductCreateDTO;
import gaming.pe.Entity.Product;
import gaming.pe.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.Optional;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        Optional<Product> optional = productService.findById(id);
        return optional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> createProduct(
            @RequestParam("nameProduct") String nameProduct,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("units") Integer units,
            @RequestParam(value = "isActive", required = false, defaultValue = "true") Boolean isActive,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            // Crear DTO con los datos recibidos del formulario
            ProductCreateDTO dto = new ProductCreateDTO();
            dto.setNameProduct(nameProduct);
            dto.setDescription(description);
            dto.setPrice(price);
            dto.setUnits(units);
            dto.setIsActive(isActive);
            dto.setCategoryId(categoryId);

            // Llamar al servicio para guardar el producto
            var result = productService.save(dto, file);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(
            value = "/{id}/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Product> updateProductImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        // Llama al servicio que encapsula la lógica de Cloudinary + BD
        Product updatedProduct = productService.updateImage(id, file);
        return ResponseEntity.ok(updatedProduct);
    }

        @PutMapping(
                value = "/{id}",
                consumes = MediaType.MULTIPART_FORM_DATA_VALUE
        )
        public ResponseEntity<Product> updateProduct(
                @PathVariable Long id,
                @RequestParam(value = "nameProduct",  required = false) String nameProduct,
                @RequestParam(value = "description",  required = false) String description,
                @RequestParam(value = "price",        required = false) Double price,
                @RequestParam(value = "units",        required = false) Integer units,
                @RequestParam(value = "isActive",     required = false) Boolean isActive,
                @RequestParam(value = "categoryId",   required = false) Long categoryId,
                @RequestParam(value = "file",         required = false) MultipartFile file
        ) {
            try {
                // Crear DTO con los datos recibidos
                ProductCreateDTO dto = new ProductCreateDTO();
                dto.setNameProduct(nameProduct);
                dto.setDescription(description);
                dto.setPrice(price);
                dto.setUnits(units);
                dto.setIsActive(isActive);
                dto.setCategoryId(categoryId);

                // Llamar al servicio para actualizar el producto usando el ID
                Product updatedProduct = productService.update(id, dto, file);

                return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws IOException {
        Optional<Product> book = productService.findById(id);
        if (book.isPresent()) {
            productService.delete(book.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

