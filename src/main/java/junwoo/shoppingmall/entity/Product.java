package junwoo.shoppingmall.entity;

import groovy.util.logging.Slf4j;
import jakarta.persistence.*;
import junwoo.shoppingmall.dto.ProductDTO;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@Component
@Getter
@NoArgsConstructor
@Slf4j
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int fileAttached;

    private int price;

    private int stockQuantity;

    private String storedFilename;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductImage> productImageList = new ArrayList<>();


    public static Product toEntity(ProductDTO dto)
    {
        Product product = Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stockQuantity(dto.getStockQuantity())
                .fileAttached(0)
                .build();

        return product;


    }

    public static Product toSaveFileEntity(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setFileAttached(1); // 파일 있음.
        return product;
    }

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }


    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;

        this.stockQuantity = restStock;
    }







}
