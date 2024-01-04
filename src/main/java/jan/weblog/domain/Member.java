package jan.weblog.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String memberEmail;
    private String memberPassword;
    private String memberName;

    @Enumerated(value = EnumType.STRING)
    private Status memberStatus;

//    @ElementCollection(fetch = FetchType.LAZY)
//    @Builder.Default
//    private Set<MemberRole> roleSet = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)  // 부모엔티티 삭제시 자식엔티티도 삭제
    private List<Post> posts = new ArrayList<>();

    // 회원 생성
    @Builder
    public Member(String memberEmail, String memberName, String memberPassword) throws BadRequestException{
        if(StringUtils.isEmpty(memberEmail)) throw new BadRequestException("Member.memberEmail 누락");
        if(StringUtils.isEmpty(memberName)) throw new BadRequestException("Member.memberName 누락");
        if(StringUtils.isEmpty(memberPassword)) throw new BadRequestException("Member.memberPassword 누락");

        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberStatus = Status.EXIST;
    }


    // 회원 정보 수정

    // 회원 탈퇴
    public void deleteMember() {
        if(this.getMemberStatus() == Status.EXIST) {
            throw new IllegalStateException("이미 삭제된 게시글입니다.");
        }

        this.memberStatus = Status.NOT_EXIST;
    }





}
