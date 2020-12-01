package cn.changemax.ddd.interfaces.config.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

/**
 * @author WangJi
 * @Description controller切面
 * @Date 2020/12/1 14:20
 */
@Slf4j
@Aspect
@Configuration
public class ControllerAspect {

    @Pointcut("execution(public * cn.changemax.ddd.interfaces.facade..*.*(..))")
    public void aspect() {
    }

    @Before("aspect()")
    public void doBefore(JoinPoint joinPoint) {
        PrintApiSystemLog.printDoBefore(joinPoint);
    }

    /**
     * 后置通知(在方法执行之后并返回数据) 用于拦截Controller层无异常的操作
     *
     * @param joinPoint 切点
     */
    @AfterReturning("aspect()")
    public void after(JoinPoint joinPoint) {
    }

    @Around(value = "aspect()")
    public Object validationPoint(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed(pjp.getArgs());
        //打印日志
        PrintApiSystemLog.printValidationPoint(start, result, pjp);
        return result;
    }

    @After("aspect()")
    public void doAfter() {
        PrintApiSystemLog.printDoAfter();
    }
}

