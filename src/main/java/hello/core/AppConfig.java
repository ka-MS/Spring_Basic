package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // @Bean memberService() 호출 -> new MemoryMemberRepository() 생성
    // @Bean orderService() -> new MemoryMemberRepository()
    // 두 객체가 new MemoryMemberRepository()인스턴스를 두번 생성하는데
    // 이렇게 되면 싱글톤이 깨지는것 아닌가???
    static int i = 0;
    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    } // 의존성 -> 구현체 주입 어떤 db를 사용할 것인지 주입해줌

    public static MemberRepository memberRepository2 = new MemoryMemberRepository();
    @Bean
    public static MemberRepository memberRepository3(){
        return new MemoryMemberRepository();
    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        i++;
        System.out.println(i);
        return new MemoryMemberRepository();
    }
    // 중복된 객체 선언부를 제거하여 한 Repository의 변경이 있을 때 한 부분만 변경하면 된다
    // 구성정보가 한눈에 들어오도록 변경하여 역할과 구현클래스가 한눈에 들어온다
    // 애플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악 가능

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }// 의존성 주입 어떤 할인 정책을 사용할 것인지, 어떤 저장소를 사용할 것인지 주입

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new RateDiscountPolicy();
        return new FixDiscountPolicy();
    }
}
