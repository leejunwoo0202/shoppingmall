package junwoo.shoppingmall.controller;

import junwoo.shoppingmall.dto.OrderSearch;
import junwoo.shoppingmall.entity.Member;
import junwoo.shoppingmall.entity.Order;
import junwoo.shoppingmall.entity.Product;
import junwoo.shoppingmall.service.MemberService;
import junwoo.shoppingmall.service.OrderService;
import junwoo.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final MemberService memberService;
    private final ProductService productService;

    private final OrderService orderService;

    @GetMapping("/order")
    public String createForm(Model model) {

        List<Member> members = memberService.findMembers();
        List<Product> products = productService.findProducts();

        model.addAttribute("members", members);
        model.addAttribute("products", products);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("productId") Long productId,
                        @RequestParam("count") int count) {

        orderService.order(memberId, productId, count);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);

        return "order/orderList";
    }
}
