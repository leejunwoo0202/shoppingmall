package junwoo.shoppingmall.repository;

import junwoo.shoppingmall.dto.OrderSearch;
import junwoo.shoppingmall.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(" select o from Order o join o.member m")
    public List<Order> findOrderLists(OrderSearch orderSearch);




}
