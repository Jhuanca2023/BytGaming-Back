package gaming.pe.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDTO {
    private Long    id;
    private String  nameProduct;
    private String  description;
    private Double  price;
    private Integer units;
    private Boolean isActive;
    private Long    categoryId;
    private Long    imageId;
}
