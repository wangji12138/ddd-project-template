package cn.changemax.ddd.infrastructure.base;

/**
 * @author WangJi
 * @Description 异常基类
 * @Date 2020/12/1 14:02
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 6115965558306627829L;

    /**
     * 默认业务错误
     */
    private static final int OTHER_FAILED = 1000;
    /**
     * 默认错误码
     */
    private static final Long DEFAULT_CODE = 10000000001L;

    /**
     * 错误码
     */
    private Long code;
    /**
     *错误信息
     */
    private String msg;

    public BaseException(String appCode, long code, String msg) {
        this.code = this.combineCode(appCode, code);
        this.msg = msg;
    }

    public BaseException(String appCode, String msg) {
        this.code = this.combineCode(appCode, OTHER_FAILED);
        this.msg = msg;
    }

    public BaseException(String msg) {
        this.code = DEFAULT_CODE;
        this.msg = msg;
    }

    public Long getCode() {
        return this.code;
    }

    public String getMsg() {
        return msg;
    }

    private long combineCode(String appCode, long exCode) {
        return Long.parseLong(appCode + exCode);
    }
}

