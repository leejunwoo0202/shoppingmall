package junwoo.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

    private String memberName; //회원 이름
    private Long orderItemName; // 주문 상품 이름
}
