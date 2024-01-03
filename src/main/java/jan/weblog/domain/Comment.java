package jan.weblog.domain;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String comment;    // 댓글 내용


    /* 연관관계편의메서드 */
    public void setPost(Post post) {
        this.post = post;
        post.getComment().add(this);
    }


}
