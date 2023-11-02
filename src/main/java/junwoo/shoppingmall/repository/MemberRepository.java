package junwoo.shoppingmall.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import junwoo.shoppingmall.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    public List<Member> findByName(String name);


}
