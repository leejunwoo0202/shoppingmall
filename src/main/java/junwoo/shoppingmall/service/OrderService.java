package junwoo.shoppingmall.service;

import jakarta.transaction.Transactional;
import junwoo.shoppingmall.dto.OrderSearch;
import junwoo.shoppingmall.entity.Member;
import junwoo.shoppingmall.entity.Order;
import junwoo.shoppingmall.entity.OrderItem;
import junwoo.shoppingmall.entity.Product;
import junwoo.shoppingmall.repository.MemberRepository;
import junwoo.shoppingmall.repository.OrderRepository;
import junwoo.shoppingmall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    private final ProductRepository productRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long productId, int count) {

        //엔티티 조회
        Member member = memberRepository.findById(memberId).orElseThrow(NullPointerException::new);
        Product product = productRepository.findById(productId).orElseThrow(NullPointerException::new);




        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(product, product.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, orderItem);

        //주문 저장
        orderRepository.save(order);

        return order.getId();
    }


    public List<Order> findOrders(OrderSearch orderSearch) {

        return orderRepository.findOrderLists(orderSearch);


    }

}
