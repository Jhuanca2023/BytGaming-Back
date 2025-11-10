package gaming.pe.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCreateDTO {

    private String nameProduct;
    private String description;
    private Double price;
    private Integer units;
    private Boolean isActive;
    private Long categoryId;


}
