package jan.weblog.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    Set<Comment> comments = new HashSet<>();

    /* 연관관계편의메서드 */

    // 게시글에 대한 멤버 set
    public void setMember(Member member) {
        this.member = member;
        member.getPosts().add(this);
    }

    // 게시글에 댓글 추가(댓글과 게시글 연관관계매핑 메서드)
    public void addComment(Comment comment) {
        this.getComments().add(comment);
        comment.setPost(this);
    }

    //== 생성 메서드==//
    public static Post createPost(Member member) {
        Post post = new Post();
        post.setMember(member);

        return post;
    }

}
