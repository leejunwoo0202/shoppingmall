package junwoo.shoppingmall.controller;

import junwoo.shoppingmall.dto.ProductImageDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductForm {

    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @Builder.Default
    private List<ProductImageDTO> imageDTOList = new ArrayList<>();


}
