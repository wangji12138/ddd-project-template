package cn.changemax.ddd.interfaces.exception;

import cn.changemax.ddd.infrastructure.base.BaseException;
import lombok.extern.slf4j.Slf4j;

/**
 * 统一异常类
 *
 * @author WangJi
 */
@Slf4j
public class CmException extends BaseException {

    private static final long serialVersionUID = -9125001762987556698L;

    public CmException(long code, String msg) {
        super(ApiCode.APP_CODE, code, msg);
    }

    public CmException(String msg) {
        super(ApiCode.APP_CODE, msg);
    }
}
