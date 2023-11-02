package junwoo.shoppingmall.dto;

import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import junwoo.shoppingmall.entity.Address;
import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class MemberDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long age;
    private String name;

    @Embedded
    private Address address;

    public MemberDTO(int age, String name, Address address) {
    }
}
