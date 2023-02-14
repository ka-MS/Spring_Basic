package hello.core.singleton;

public class SingletonService {
    private  static  final  SingletonService instance = new SingletonService();
    // 스태틱으로 만들면 스태틱 영역에 올라가게 되어 딱 하나만 존재하게됨!
    // private니까 외부에서 수정도 안됨
    // 이렇게 선언하면 자기자신을 선언해서 변수에 참조를 넣어놓음

    public static SingletonService getInstance() {
        return instance;
    }// 조회용 -> 인스턴스 객체를 꺼내는 방법은 조회용 메소드를 사용하는수 밖에 없음

    private SingletonService() {
        // private 생성자를 만들면!!! 외부에서 생성할 수 없다
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
