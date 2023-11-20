package junwoo.shoppingmall.service;

import jakarta.transaction.Transactional;
import junwoo.shoppingmall.dto.ProductDTO;
import junwoo.shoppingmall.dto.entity.Product;
import junwoo.shoppingmall.dto.entity.ProductImage;
import junwoo.shoppingmall.repository.ProductImageRepository;
import junwoo.shoppingmall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;

    private final ProductImageRepository productImageRepository;


    public List<Product> findProducts()
    {
        return productRepository.findAll();
    }

    public void saveProduct(ProductDTO dto) throws IOException {
        if(dto.getMultipartFile().isEmpty())
        {
            Product product = Product.toEntity(dto);


            productRepository.save(product);
            System.out.println("파일첨부 x");

        }
        else
        {
            MultipartFile productFile = dto.getMultipartFile();
            String originalFileName = productFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
            String savePath = "C:/springboot_img/" + storedFileName;
            productFile.transferTo(new File(savePath));
            Product productEntity = Product.toSaveFileEntity(dto);
            Long savedId = productRepository.save(productEntity).getId();
            Product product = productRepository.findById(savedId).get();

            ProductImage productFileEntity =
                    ProductImage.toProductImage(originalFileName, storedFileName, product);

            productImageRepository.save(productFileEntity);
            System.out.println("파일첨부 o");
        }




    }


}
