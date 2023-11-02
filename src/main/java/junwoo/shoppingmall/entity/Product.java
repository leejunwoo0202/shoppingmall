package junwoo.shoppingmall.entity;

import groovy.util.logging.Slf4j;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import junwoo.shoppingmall.dto.ProductDTO;
import junwoo.shoppingmall.dto.ProductImageDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Builder
@AllArgsConstructor
@Component
@Getter
@NoArgsConstructor
@Slf4j
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String originFileName;
    private String storeFileName;

    private int price;

    private int stockQuantity;




    public Map<String, Object> toEntity(ProductDTO dto)
    {
        Map<String, Object> entityMap = new HashMap<>();

        Product product = Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stockQuantity(dto.getStockQuantity())
                .build();

        entityMap.put("product", product);



        List<ProductImageDTO> imageDTOList = dto.getImageDTOList();

        if(imageDTOList != null && imageDTOList.size() > 0 )
        {
            ProductImageDTO productImageDTO = new ProductImageDTO();

            List<ProductImage> productImageList = (List<ProductImage>) new ProductImage(
                    productImageDTO.getUuid(), productImageDTO.getImgName(),
                        productImageDTO.getPath(), product);
            entityMap.put("imgList", productImageList);
        }

        return entityMap;

    }




}
