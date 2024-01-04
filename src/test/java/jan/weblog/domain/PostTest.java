package jan.weblog.domain;

import jan.weblog.repository.MemberRepository;
import jan.weblog.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;


@SpringBootTest
@Rollback
class PostTest {

//    @Autowired
//    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    public void 게시글_생성() throws Exception {
        // given
        Member member = Member.builder()
                .memberEmail("jkchoi")
                .memberPassword("123456")
                .memberName("최준근")
                .build();

        //  정상 게시글
//        Post post = Post.builder()
//                .title("타이틀1")
//                .content("컨텐츠1")
//                .member(member)
//                .build();


        memberService.join(member);




        // 필수 조건 누락 게시글_1
//        Post deadPost_1 = Post.builder()
//                .title("")
//                .content("컨텐츠만 있다")
//                .member(member)
//                .build();

        // 필수 조건 누락 게시글_2
//        Post deadPost_2 = Post.builder()
//                .title("제목만 있다")
//                .content("")
//                .member(member)
//                .build();

        // 필수 조건 누락 게시글_3
//        Post deadPost_3 = Post.builder()
//                .title("제목 있다")
//                .content("컨텐츠 있고 멤버가 없다")
//                .build();


        // when

        // then
     }

     @Test
     public void 테스트() throws Exception {
         // given

         // when

         // then
      }

}