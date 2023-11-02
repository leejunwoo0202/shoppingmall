package junwoo.shoppingmall.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private Long id;

    private String name;

    private  int price;

    private int stockQuantity;

    @Builder.Default
    private List<ProductImageDTO> imageDTOList = new ArrayList<>();

    public ProductDTO(String name, int price, int stockQuantity, List<ProductImageDTO> imageDTOList) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageDTOList = imageDTOList;
    }
}
