package jan.weblog.service;

import jan.weblog.domain.Member;
import jan.weblog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 회원가입
     * */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByMemberEmail(member.getMemberEmail());
        System.out.println("듀플리케이트???");
        if(!findMembers.isEmpty()) {
            System.out.println("듀플리케이트2222222???");
            throw new IllegalStateException("이미 존재하는 회원아이디입니다.");
        }
    }
}
