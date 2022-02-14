package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.repository.SpringDataJpaMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest//db연동 시 테스트 하기 위한 어노테이션
@Transactional//mysql start transaction 기능 > Test에 붙었을때만 롤백함. @Service, @Repository 에 붙으면 rollback되지 않음.
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired SpringDataJpaMemberRepository memberRepository;


    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName().equals(findMember.getName()));
    }

    @Test
    public void joinExceptionTest(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when

        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat((e.getMessage()).equals(memberService.findMembers()));
        /*try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e){
            assertThat(e.getMessage().equals("이미 존재하는 회원입니다."));
        }*/
        //then
    }

}