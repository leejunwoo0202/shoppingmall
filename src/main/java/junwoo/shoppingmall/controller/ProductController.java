package junwoo.shoppingmall.controller;

import junwoo.shoppingmall.dto.ProductDTO;
import junwoo.shoppingmall.entity.Product;
import junwoo.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public String createProduct(ProductForm form, @RequestParam("image") MultipartFile file, Model model) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        model.addAttribute("msg", "Uploaded images: " + fileNames.toString());

        /*ProductDTO productDTO = new ProductDTO(
                form.getName(), form.getPrice(), form.getStockQuantity(),form.getImageDTOList());

        System.out.println(" form.getImageDTOList() = " + form.getImageDTOList());
        Long id = productService.saveProduct(productDTO);*/


        return "redirect:/";

    }


    @GetMapping("/products")
    public String list(Model model) {
        List<Product> products = productService.findProducts();
        model.addAttribute("products", products);
        return "/product/list";
    }


}
