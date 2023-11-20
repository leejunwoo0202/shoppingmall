package junwoo.shoppingmall.controller;


import junwoo.shoppingmall.dto.ProductDTO;
import junwoo.shoppingmall.dto.entity.Product;
import junwoo.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    public static String UPLOAD_DIRECTORY = "C:/images";

    @GetMapping("/products/new")
    public String createForm(Model model)
    {
        model.addAttribute("form", new ProductForm());
        return "/product/createProductForm";
    }

    @PostMapping("/products/new")
    public String createProduct(@ModelAttribute ProductDTO dto) throws IOException {


        productService.saveProduct(dto);


        return "redirect:/";

    }


    @GetMapping("/products")
    public String list(Model model) {
        List<Product> products = productService.findProducts();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for(Product product : products) 
        {
            productDTOs.add(ProductDTO.toProductDTO(product));
        }
        model.addAttribute("products", productDTOs);
        return "/product/list";
    }


}
