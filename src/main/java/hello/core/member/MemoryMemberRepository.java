package hello.core.member;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new ConcurrentHashMap<>();
    // 스레드 세이프한 concurrentHashMap
    //

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    } //store Map객체에 아이디랑 멤버를 저장하여 메모리 임시저장소로 사용

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
