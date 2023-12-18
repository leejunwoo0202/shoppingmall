package junwoo.shoppingmall.controller;


import junwoo.shoppingmall.dto.ProductDTO;
import junwoo.shoppingmall.entity.Product;
import junwoo.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    public String UPLOAD_DIRECTORY = "C:/images";

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

    @GetMapping("/product/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {

        Page<ProductDTO> productList = productService.paging(pageable);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < productList.getTotalPages()) ? startPage + blockLimit - 1 : productList.getTotalPages();


        model.addAttribute("productList", productList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "/product/paging";

    }


}
