package junwoo.shoppingmall.controller;

import junwoo.shoppingmall.entity.Member;
import junwoo.shoppingmall.entity.Product;
import junwoo.shoppingmall.service.MemberService;
import junwoo.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final MemberService memberService;
    private final ProductService productService;

    @GetMapping("/order")
    public String createForm(Model model) {

        List<Member> members = memberService.findMembers();
        List<Product> products = productService.findProducts();

        model.addAttribute("members", members);
        model.addAttribute("products", products);

        return "order/orderForm";
    }
}
