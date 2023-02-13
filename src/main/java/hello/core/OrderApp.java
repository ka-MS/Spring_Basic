package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = context.getBean(MemberService.class);
        OrderService orderService = context.getBean(OrderService.class);

        Long memberId = 1L;
        Member member = new Member(1L, "memberB", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(member.getId(),"양말",30000);

        System.out.println("order = " + order);

    }
}
