package kr.boot.basic.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component // 스프링 컴포넌트 스캔에 의해서 객체를 미리 생성한다
public class TimeTraceAop {
}
