package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
// 시간을 측정하는 별도의 공통 로직을 구현
// 핵심 관심 사항을 깔끔하게 유지 가능
public class TimeTraceAop {

    // AOP를 적용할 대상(메서드)을 지정
    @Around("execution(* hello.hello_spring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        System.out.println("START: "+ joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END: "+joinPoint.toString()+ " "+ timeMs + "ms");

        }


    }

}
