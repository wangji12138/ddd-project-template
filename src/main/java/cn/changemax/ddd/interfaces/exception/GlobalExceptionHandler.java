package cn.changemax.ddd.interfaces.exception;

import cn.changemax.utils.ApiResult;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 统一异常处理
 *
 * @author WANGJI
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 判断是否异步请求
     * @param request
     * @return
     */
    private static boolean isAjaxRequest(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        String xRequestedWith = request.getHeader("X-Requested-With");
        // 如果是异步请求或是手机端，则直接返回信息
        return ((accept != null && accept.contains("application/json") || (xRequestedWith != null && xRequestedWith.contains("XMLHttpRequest"))));
    }

    //    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ApiResult<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest request) {
        log.error("缺少请求参数 [method={}\turl={}\tquery={}\tmsg={}]", request.getMethod(), request.getRequestURI(), request.getQueryString(), e.getMessage());
        String message = "【缺少请求参数】" + e.getMessage();
        return ApiResult.fail(message);
    }

    //    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ApiResult<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        log.error("参数解析失败 [method={}\turl={}\tquery={}\tmsg={}]", request.getMethod(), request.getRequestURI(), request.getQueryString(), e.getMessage());
        String message = "【参数解析失败】" + e.getMessage();
        return ApiResult.fail(message);
    }

    //    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiResult<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        FieldError error = e.getBindingResult().getFieldError();
        assert error != null;
        String message = "【参数验证失败】" + String.format("%s:%s", error.getField(), error.getDefaultMessage());
        log.error("参数验证失败 [method={}\turl={}\tquery={}\tmsg={}]", request.getMethod(), request.getRequestURI(), request.getQueryString(), e.getMessage());
        return ApiResult.fail(message);
    }

    //    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ApiResult<?> handleServiceException(ConstraintViolationException e, HttpServletRequest request) {
        String message = "【参数验证失败】" + e.getMessage();
        log.error("服务端错误 [method={}\turl={}\tquery={}\tmsg={}]", request.getMethod(), request.getRequestURI(), request.getQueryString(), e.getMessage());
        return ApiResult.fail(message);
    }

    //    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ApiResult<?> handleValidationException(ValidationException e, HttpServletRequest request) {
        String message = "【验证失败】" + e.getMessage();
        log.error("验证失败 [method={}\turl={}\tquery={}\tmsg={}]", request.getMethod(), request.getRequestURI(), request.getQueryString(), e.getMessage());
        return ApiResult.fail(message);
    }

    //    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ApiResult<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        String message = "【不支持当前请求方法】" + e.getMessage();
        log.error("不支持当前请求方法 [method={}\turl={}\tquery={}\tmsg={}]", request.getMethod(), request.getRequestURI(), request.getQueryString(), e.getMessage());
        return ApiResult.fail(message);
    }

    //    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public ApiResult<?> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e, HttpServletRequest request) {
        String message = "【不支持当前媒体类型】" + e.getMessage();
        log.error("不支持当前媒体类型 [method={}\turl={}\tquery={}\tmsg={}]", request.getMethod(), request.getRequestURI(), request.getQueryString(), e.getMessage());
        return ApiResult.fail(message);
    }

    //    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ApiResult<?> handleBindException(BindException e, HttpServletRequest request) {
        FieldError error = e.getBindingResult().getFieldError();
        assert error != null;
        String message = "【参数绑定失败】" + String.format("%s:%s", error.getField(), error.getDefaultMessage());
        log.error("参数绑定失败 [method={}\turl={}\tquery={}\tmsg={}]", request.getMethod(), request.getRequestURI(), request.getQueryString(), e.getMessage());
        return ApiResult.fail(message);
    }

    //    // 捕捉shiro的异常
////    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    @ExceptionHandler(ShiroException.class)
//    @ResponseBody
//    public ApiResult<?> handle401(ShiroException e, HttpServletRequest request) {
//        log.error("服务端错误 [method={}\turl={}\tquery={}\tmsg={}]", request.getMethod(), request.getRequestURI(), request.getQueryString(), e.getMessage());
//        return ApiResult.fail(e.getMessage());
//    }
//
    // 捕捉UnauthorizedException
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public ApiResult<?> handle401(UnauthorizedException ex, HttpServletRequest request) {
        log.error("【权限异常】 [method={}\turl={}\tquery={}\tmsg={}]", request.getMethod(), request.getRequestURI(), request.getQueryString(), ex.getMessage());
        return ApiResult.fail(Long.parseLong(ApiCode.APP_CODE + ApiCode.LOGIN_UNAUTHORIZED_ERROR), "缺少权限，请联系管理员：" + ex.getMessage());
    }

    @ExceptionHandler({Exception.class})
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String defaultErrorHandler(Exception e, HttpServletRequest request, HttpServletResponse response) {
        log.error("服务端错误 [method={}\turl={}\tquery={}]", request.getMethod(), request.getRequestURI(), request.getQueryString());
        log.error("完整堆栈信息：");
        e.printStackTrace();

        if (isAjaxRequest(request)) {
            // 输出JSON
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            try (PrintWriter writer = response.getWriter()) {
                writer.append(JSON.toJSONString(ApiResult.fail(ApiCode.SERVER_ERROR, "系统错误，请联系站长." + e.getMessage())));
            } catch (IOException ioe) {
                log.error("返回Response信息出现IOException异常:" + ioe.getMessage());
            }
            return null;
        } else {
            return "forward:/500/page";
        }
    }

    //    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = CmException.class)
    @ResponseBody
    public ApiResult<?> businessException(CmException e, HttpServletRequest request) {
        log.error("服务端错误 [method={}\turl={}\tquery={}\tmsg={}]", request.getMethod(), request.getRequestURI(), request.getQueryString(), e.getMessage());
        log.error("CmException完整堆栈信息：");
        e.printStackTrace();
        //自定义异常抛出
        return ApiResult.fail(e.getCode(), e.getMsg());
    }

}
