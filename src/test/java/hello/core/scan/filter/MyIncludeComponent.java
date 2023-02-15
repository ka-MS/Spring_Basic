package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
    // 해당 어노테이션이 붙으면 컴포넌트 스캔에서 제외할거라는 의미

}
