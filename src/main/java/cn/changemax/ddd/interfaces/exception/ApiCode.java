package cn.changemax.ddd.interfaces.exception;

/**
 * @author WangJi
 * @Description 状态码
 * @Date 2020/12/1 14:03
 */
public interface ApiCode {

    /**
     * changemax生态核心服务:: appCode
     */
    String APP_CODE = "10000";

    /**
     * 默认200为成功
     */
    long SUCCESS = Long.parseLong(APP_CODE + 200);

    /**
     * 默认400为失败
     */
    long FAIL = Long.parseLong(APP_CODE + 400);

    /**
     * 默认500系统错误
     */
    long SERVER_ERROR = Long.parseLong(APP_CODE + 500);

}
