package junwoo.shoppingmall.controller;

import junwoo.shoppingmall.entity.Product;
import junwoo.shoppingmall.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public String list(Model model) {
        List<Product> items = productService.findAll();
        model.addAttribute("products", items);
        return "/product/list";
    }
}
