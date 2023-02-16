package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        basePackages = "hello.core.member", //멤버패키지 안에있는것만 등록
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION
                , classes = Configuration.class)
)
// @ComponentScan은 @Component 를 모두 찾아서 빈으로 등록해줌.
// Configuration또한 @Component를 내부에 가지고 있다
// 작성했던 수동설정 클래스나 테스트설정 클래스는 모두 제외해 줘야함
// 보통 실무에서는 컴포넌트를 스캔대상에서 제외하지는 않지만 기존코드를 최대한 남기고 유기하기 위함!
public class AutoAppConfig {
// 내부에 @Bean이 하나도 없다!


    // 빈 이름 충돌 테스트
//    @Bean("memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
