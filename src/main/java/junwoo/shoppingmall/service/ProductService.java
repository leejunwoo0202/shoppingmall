package junwoo.shoppingmall.service;

import jakarta.transaction.Transactional;
import junwoo.shoppingmall.dto.ProductDTO;
import junwoo.shoppingmall.entity.Product;
import junwoo.shoppingmall.entity.ProductImage;
import junwoo.shoppingmall.repository.ProductImageRepository;
import junwoo.shoppingmall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;

    private final ProductImageRepository productImageRepository;

    public Page<ProductDTO> paging(Pageable pageable) {

        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3; // 한 페이지에 보여줄 글 갯수
        // 한페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0부터 시작
        Page<Product> productEntities =
                productRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        System.out.println("boardEntities.getContent() = " + productEntities.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("boardEntities.getTotalElements() = " + productEntities.getTotalElements()); // 전체 글갯수
        System.out.println("boardEntities.getNumber() = " + productEntities.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("boardEntities.getTotalPages() = " + productEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardEntities.getSize() = " + productEntities.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("boardEntities.hasPrevious() = " + productEntities.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("boardEntities.isFirst() = " + productEntities.isFirst()); // 첫 페이지 여부
        System.out.println("boardEntities.isLast() = " + productEntities.isLast()); // 마지막 페이지 여부

        ProductDTO productDTO = new ProductDTO();


        Page<ProductDTO> productDTOS = productEntities.map(
                product -> new ProductDTO(product.getName(),product.getPrice(), product.getStockQuantity()
                        ));



        for (Product product : productEntities) {
            productDTO.setName(product.getName());
            productDTO.setPrice(product.getPrice());
            productDTO.setStockQuantity(product.getStockQuantity());

            if (product.getFileAttached() == 0) {
                productDTO.setFileAttached(product.getFileAttached()); // 0
            } else {
                productDTO.setFileAttached(product.getFileAttached()); // 1

                productDTO.setOriginalFileName(product.getProductImageList().get(0).getOriginalFileName());
                productDTO.setStoredFileName(product.getProductImageList().get(0).getStoredFileName());
            }
        }



        return productDTOS;

    }


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
