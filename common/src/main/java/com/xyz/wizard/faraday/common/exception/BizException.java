package com.xyz.wizard.faraday.common.exception;

import com.xyz.wizard.faraday.common.result.IResultCode;
import lombok.Data;

/**
 * 描述
 *
 * @author wizard
 * @version 1.0
 * @date 2021/09/30 16:57:15
 */
@Data
public class BizException extends RuntimeException {

    public IResultCode resultCode;

    public BizException(IResultCode errorCode) {
        super(errorCode.getMsg());
        this.resultCode = errorCode;
    }

    public BizException(String message){
        super(message);
    }

    public BizException(String message, Throwable cause){
        super(message, cause);
    }

    public BizException(Throwable cause){
        super(cause);
    }
}
