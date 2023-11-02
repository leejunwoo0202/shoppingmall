package junwoo.shoppingmall.service;

import jakarta.transaction.Transactional;
import junwoo.shoppingmall.dto.ProductDTO;
import junwoo.shoppingmall.entity.Product;
import junwoo.shoppingmall.entity.ProductImage;
import junwoo.shoppingmall.repository.ProductImageRepository;
import junwoo.shoppingmall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;

    private final ProductImageRepository productImageRepository;
    private final Product product;

    public List<Product> findProducts()
    {
        return productRepository.findAll();
    }

    public Long saveProduct(ProductDTO dto)
    {
        Map<String, Object> entityMap = product.toEntity(dto);
        Product product = (Product)entityMap.get("product");
        List<ProductImage> productImageList = (List<ProductImage>) entityMap.get("imgList");



        productRepository.save(product);

        productImageList.forEach(productImage -> {
            productImageRepository.save(productImage);
        });

        return product.getId();

    }


}
