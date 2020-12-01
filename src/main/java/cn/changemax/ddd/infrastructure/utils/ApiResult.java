package cn.changemax.ddd.infrastructure.utils;

import cn.changemax.ddd.interfaces.exception.ApiCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author WangJi
 * @Description 接口出参
 * @Date 2020/12/1 14:05
 */
@Data
@ApiModel
public final class ApiResult<T> {

    @ApiModelProperty(value = "请求响应code，200为成功 其他为失败", name = "code")
    private long code;

    @ApiModelProperty(value = "需要返回的数据", name = "data")
    private T data;

    @ApiModelProperty(value = "响应异常码详细信息", name = "msg")
    private String msg;

    @ApiModelProperty(value = "当前服务器时间", name = "currentTime")
    private long currentTime = System.currentTimeMillis();

    private ApiResult() {
        throw new RuntimeException("what are you 弄啥嘞？");
    }

    public ApiResult(long code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static <T> ApiResult<T> success() {
        return new ApiResult<T>(ApiCode.SUCCESS, null, null);
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<T>(ApiCode.SUCCESS, data, null);
    }

    public static <T> ApiResult<T> success(T data, String msg) {
        return new ApiResult<T>(ApiCode.SUCCESS, data, msg);
    }

    public static <T> ApiResult<T> fail(long code, String msg) {
        return new ApiResult<T>(code, null, msg);
    }

    public static <T> ApiResult<T> fail(String msg) {
        return new ApiResult<T>(ApiCode.FAIL, null, msg);
    }

    @Override
    public String toString() {
        return "ApiResult[data=" + this.data + ", msg=" + this.msg + ", code=" + this.code + "]";
    }
}
