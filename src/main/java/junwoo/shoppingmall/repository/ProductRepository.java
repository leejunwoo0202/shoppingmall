package junwoo.shoppingmall.repository;

import junwoo.shoppingmall.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
