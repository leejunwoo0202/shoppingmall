package junwoo.shoppingmall.repository;

import junwoo.shoppingmall.dto.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
