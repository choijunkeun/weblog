package jan.weblog.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.coyote.BadRequestException;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;   // 제목

    @Column(nullable = false, length = 10000)
    private String content; // 내용

    @Enumerated(value = EnumType.STRING)
    private Status postStatus;

    @Column(columnDefinition = "bigint default 0", nullable = false)
    private Long hit; // 조회수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    Set<Reply> replies = new HashSet<>();    // 댓글

    /* 연관관계편의메서드 */

    // 게시글에 대한 멤버 set
    public void setMember(Member member) {
        this.member = member;
        member.getPosts().add(this);
    }

    // 게시글에 댓글 추가(댓글과 게시글 연관관계매핑 메서드)
    public void addComment(Reply reply) {
        this.getReplies().add(reply);
        reply.setPost(this);
    }


    // 게시글 생성
    @Builder
    public Post(String title, String content, Member member) throws BadRequestException {
        if(StringUtils.isEmpty(title)) throw new BadRequestException("Post.title 누락");
        if(StringUtils.isEmpty(content)) throw new BadRequestException("Post.content 누락");
        if(member == null) throw new BadRequestException("Post.member 누락");

        this.title = title;
        this.content = content;
        this.member = member;
        this.hit = 0L;
        this.postStatus = Status.EXIST;
    }

    // 게시글 수정
    public void editPost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 게시글 삭제
    public void deletePost() {
        if(this.getPostStatus() == Status.EXIST) {
            throw new IllegalStateException("이미 삭제된 게시글입니다.");
        }

        this.postStatus = Status.NOT_EXIST;
    }

}
