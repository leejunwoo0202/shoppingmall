package junwoo.shoppingmall.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import junwoo.shoppingmall.dto.entity.Product;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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

    private MultipartFile multipartFile;

    private String originalFileName;

    private String storedFileName;

    private int fileAttached;

    @Builder.Default
    private List<ProductImageDTO> imageDTOList = new ArrayList<>();

    public ProductDTO(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;

    }

    public static ProductDTO toProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setStockQuantity(product.getStockQuantity());

        if (product.getFileAttached() == 0) {
            productDTO.setFileAttached(product.getFileAttached()); // 0
        } else {
            productDTO.setFileAttached(product.getFileAttached()); // 1
            // 파일 이름을 가져가야 함.
            // originalFileName, storedFileName : board_file_table(BoardFileEntity)
            // join
            // select * from board_table b, board_file_table bf where b.id=bf.board_id
            // and where b.id=?
            productDTO.setOriginalFileName(product.getProductImageList().get(0).getOriginalFileName());
            productDTO.setStoredFileName(product.getProductImageList().get(0).getStoredFileName());
        }

        return productDTO;
    }
}
