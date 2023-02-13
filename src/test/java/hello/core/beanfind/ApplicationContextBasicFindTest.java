package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService",MemberService.class); // 빈이름으로 조회

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); //그 객체가 맞니? isInstanceOf
    }
    
    @Test
    @DisplayName("빈타입으로 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class); // 속성으로 검색
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        MemberService memberService = ac.getBean("memberService",MemberServiceImpl.class); // 속성으로 검색
        // 구체클래스를 적어도 리턴타입이 구체클래스이기 때문에 검색은 된다
        // 하지만 구체클래스를 사용하는것은 구현에 의존하는것이기 때문에 유연성이 떨어진다 좋은 코드는 아니다
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈이름으로 조회 실패")
    void findBeanByNameFail() {
//        MemberService memberService = ac.getBean("memberServiceFail",MemberService.class); // 빈이름으로 조회
        // NoSuchBeanDefinitionException: No bean named 'memberServiceFail' available
//        Assertions.assertThat(memberService).isNull();
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("memberServiceFail",MemberService.class));
        // 오른쪽에 있는 코드를 실행하면 왼쪽에있는 예외가 터져야 한다는 의미
    }
}
