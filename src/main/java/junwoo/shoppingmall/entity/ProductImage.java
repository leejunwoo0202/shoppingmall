package junwoo.shoppingmall.entity;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "movie")
public class ProductImage  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum;

    private String uuid;

    private String imgName;

    private String path;

    @ManyToOne(fetch = FetchType.LAZY) //무조건 lazy로
    private Product product;


    public ProductImage(String uuid, String imgName, String path, Product product) {
        this.uuid = uuid;
        this.imgName = imgName;
        this.path = path;
        this.product = product;
    }
}