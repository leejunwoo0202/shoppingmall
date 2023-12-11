package junwoo.shoppingmall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import junwoo.shoppingmall.dto.MemberDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

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
