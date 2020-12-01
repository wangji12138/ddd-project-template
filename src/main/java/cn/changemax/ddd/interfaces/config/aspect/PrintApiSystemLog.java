package cn.changemax.ddd.interfaces.config.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author WangJi
 * @Description 打印请求日志
 * @Date 2020/12/1 14:18
 */
@Slf4j
final class PrintApiSystemLog {

    static void printDoBefore(JoinPoint joinPoint) {
        if (null == joinPoint) {
            return;
        }
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null == attributes) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        // 打印请求相关参数
        log.debug("========================================== Start ==========================================");
        // 打印请求URL
        log.debug("URL            : {}", request.getRequestURL().toString());
        // 打印http method
        log.debug("HTTP Method    : {}", request.getMethod());
        // 打印调用controller的全路径以及执行方法
        log.debug("Class Method   : {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        // 打印请求的IP
        log.debug("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        log.debug("Request Args   : {}", Arrays.toString(joinPoint.getArgs()));
    }

    static void printValidationPoint(long start, Object result, ProceedingJoinPoint joinPoint) {
        if (null == joinPoint) {
            return;
        }
        long time = System.currentTimeMillis() - start;
        // 打印出参
        log.debug("Response Args  : {}", null != result ? result.toString() : " is null");
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
        // 执行耗时
        log.debug("REST API Method: {}.{}; Time: {}ms", className, methodName, time);
    }

    static void printDoAfter() {
        log.debug("=========================================== End ===========================================");
        // 每个请求之间空一行
        log.debug("");
    }
}
