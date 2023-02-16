package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    } // 테스트 시작시마다 새롭게 객체생성 테스트들은 unstable 하기때문에 
    // 테스트의 데이터주입등으로 오류발생하지 않도록 모든 테스트가 잘 수행되게 하기 위함
    @Test
    void createOrder() {
        // given
        Long memberId = 1L;
        Member member = new Member(1L, "memberB", Grade.VIP);
        memberService.join(member);

        // when
        Order order = orderService.createOrder(member.getId(),"양말",30000);

        // then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}