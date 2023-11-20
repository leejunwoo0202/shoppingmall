package junwoo.shoppingmall.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import junwoo.shoppingmall.dto.entity.Product;
import junwoo.shoppingmall.dto.entity.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImageDTO {

    private String uuid;

    private String originalFileName;

    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public static ProductImage toBoardFileEntity(Product productEntity, String originalFileName, String storedFileName) {
        ProductImage productImage = new ProductImage();
        productImage.setOriginalFileName(originalFileName);
        productImage.setStoredFileName(storedFileName);
        productImage.setProduct(productEntity);
        return productImage;
    }

}
