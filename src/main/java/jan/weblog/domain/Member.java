package jan.weblog.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String userId;
    private String username;
    private String userPassword;
    private String grade;

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)  // 부모엔티티 삭제시 자식엔티티도 삭제
    private List<Post> posts = new ArrayList<>();


}
