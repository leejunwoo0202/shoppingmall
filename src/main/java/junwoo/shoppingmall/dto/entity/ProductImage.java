package junwoo.shoppingmall.dto.entity;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "movie")
@Setter
public class ProductImage  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalFileName;

    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


    public static ProductImage toProductImage(String originalFileName, String storedFileName, Product product) {

        ProductImage productImage = new ProductImage();
        productImage.setOriginalFileName(originalFileName);
        productImage.setStoredFileName(storedFileName);
        productImage.setProduct(product);
        return productImage;
    }
}