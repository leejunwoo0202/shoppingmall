package junwoo.shoppingmall.service;

import jakarta.transaction.Transactional;
import junwoo.shoppingmall.entity.Product;
import junwoo.shoppingmall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;

    public List<Product> findAll()
    {
        return productRepository.findAll();
    }

}
