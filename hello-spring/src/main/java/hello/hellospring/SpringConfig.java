package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1. 아래 코드는 자바 코드로 직접 스프링 빈을 등록 하는 방법!
 *
 * 2. 일반 @Controller > @Service > @Repository 어노테이션을 활용하면 @Component scan을 활용해서 @Component를 조회해서 어플리케이션 실행할 때 연결됨.
 *
 * 3. 필드주입: @Autowired private final MemberRepository memberRepository;  >> 이렇게 쓰는건 별로 안좋다!. 수정할 수가 없음.
 *
 * 4. 생성자 주입으로 해주는 것이 좋음! 요즘 트랜드
 *
 * 5. setter 주입도 있음! setter injection > 단점: public하게 노출이 될 수 있어서 안좋음. 중간에 가로채서 변경할 요지가 있음. public하기 때문에.
 *
 *
 * */
@Configuration
public class SpringConfig {

    //SpringDataJpaRepository를 만들면 알아서 인젝션이 됨.
    private final SpringDataJpaMemberRepository memberRepository;

    @Autowired
    public SpringConfig(SpringDataJpaMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    /*@Bean bean을 등록하던지 @Component로 사용하던지 둘중 하나!
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }*/

    /*private DataSource dataSource;
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/

    /*    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/



    /**
     * DI > 빈주입을 MemoryMemberRepository를 JdbcMemberRepository로 바꿔 주기만 하면 사용 할 수 있다. 개방.폐쇄 원칙(OCP, Open-Closed Principle) : 확장에는 열려있고, 수정, 변경에는 닫혀있다.
     *      기존 코드를 전혀 손대지 않고, 설정만으로 구현 클래스를 변경 할 수 있다. (다형성을 활용)
     * */
/*    @Bean
    public MemberRepository memberRepository() {
        //return new MemmoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);

    }*/
}