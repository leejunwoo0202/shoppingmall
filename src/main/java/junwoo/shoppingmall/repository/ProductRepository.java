package junwoo.shoppingmall.repository;

import junwoo.shoppingmall.entity.Member;
import junwoo.shoppingmall.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
