package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }// 오더서비스는 어떤 저장장치를 사용할것인지, 어떤 할인정책을 사용할 것인지 전혀 모른다
    // 설정 클래스에서 정해주는것 

    //     OrderService 입장에서는 할인은 모르겠고 DiscountPolicy 너가 알아서 해줘
//     할인에 대한 변경이 필요할 경우 할인쪽만 고쳐주면 됨 주문쪽을 고칠 필요 없음
//     단일 책임 원칙이 잘 적용된것
//     만약 discountPolicy 인터페이스가 없었더라면? 할인에 대한 내용이 오더에 있었을것이고
//     오더를 수정해야 할인 정책이 수정되었을 것
//     할인변경이 필요한데 오더서비스가 수정되어야 한다면 단일책임원칙이 잘 적용되지 않은것
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) { //주문 데이터 입력
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); 
        // 등급만 넘겨도 되지만 확장성을 위하여 member를 넘김
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
