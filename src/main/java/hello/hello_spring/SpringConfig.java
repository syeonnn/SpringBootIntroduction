package hello.hello_spring;

import hello.hello_spring.aop.TimeTraceAop;
import hello.hello_spring.repository.*;
import hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }

// 위의 코드 대신  @Component 추가를 통해 Spring 자동 빈 등록 수행하도록 함.
// ✅ 순환 참조 발생 가능성의 흐름
// 1️⃣ MemberController → MemberService를 의존
// 2️⃣ MemberService → SpringConfig에서 @Bean으로 등록
// 3️⃣ TimeTraceAop → SpringConfig에서 @Bean으로 등록
// 4️⃣ AOP 프록시가 MemberService를 감싸면서 Spring이 내부적으로 순환 참조를 유발할 가능성
//
//  즉, AOP 적용 과정에서 MemberService가 AOP의 대상이 되면서, 내부적으로 프록시 객체를 만들고 순환 참조가 발생할 수 있음!

}