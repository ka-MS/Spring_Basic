package hello.core.order;

import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    //순수한 자바 테스트

    @Test
    void createOrder(){
        MemberRepository memberRepository = new MemoryMemberRepository();
//        MemberService memberService = new MemberServiceImpl(memberRepository);
        Member member = new Member(1L, "", Grade.VIP);
//        memberService.join(member); //다 지정해서 만들어놔야함
        memberRepository.save(member);

//        orderService.setMemberRepository(memberRepository);
//        orderService.setDiscountPolicy(new RateDiscountPolicy());
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository,new RateDiscountPolicy());
        orderService.createOrder(1L, "", 30000);
        // 널포인트익셉션 발생-> 리포지토리에 대한 구현체가 주입되어있지않음 데이터 저장할 곳이 없어서 오류발생
        // 실행해 보기 전까지 알 수 없다
        // 생성자주입을 사용하면 컴파일 오류로 미연에 잡을 수 있다
        Order order = orderService.createOrder(1L, "", 30000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(3000);
        //스프링없이 필요한 구현체들을 생성해서 조합하여 테스트하는방식
    }

}