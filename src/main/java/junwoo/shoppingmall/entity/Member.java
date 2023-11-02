package junwoo.shoppingmall.entity;

import jakarta.persistence.*;
import junwoo.shoppingmall.dto.MemberDTO;
import junwoo.shoppingmall.dto.ProductDTO;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long age;
    private String name;

    @Embedded
    private Address address;

    public Member toEntity(MemberDTO dto)
    {
        return Member.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .address(dto.getAddress())
                .build();

    }





    public Member(int age, String name, Address address) {
    }
}
