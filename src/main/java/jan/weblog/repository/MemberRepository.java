package jan.weblog.repository;

import jan.weblog.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // select m from Member m where m.memberEmail = ?
    List<Member> findByMemberEmail(String memberEmail);
}
