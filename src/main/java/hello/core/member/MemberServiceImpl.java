package hello.core.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // MemberServiceImpl 클래스는 인터페이스인 멤버리포지토리에도 의존하고 메모리멤버리포에도 의존중임
    // 추상화에도 의존하고 구체클래스에도 의존중 dip를 위반

    @Override
    public void join(Member member) {
        memberRepository.save(member);
        // 다형성에 의해 메모리멤버리포(멤버리포의 구현체) 안의 세이브가 호출
    }

    @Override
    public Member findMemberById(Long MemberId) {
        return memberRepository.findById(MemberId);
    }
}
