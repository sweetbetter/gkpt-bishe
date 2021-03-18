package com.dong.server.basic.exception;

import com.dong.server.util.ResultCodeEnum;
import lombok.Data;

/**
 * @ClassName: OssException
 * @Author: caojingdong
 * @Description:
 * @Date: 2021/3/18 13:53
 */
@Data
public class BasicException extends  RuntimeException{

    private Integer code;

    public BasicException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public BasicException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code=resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "BasicException{" +
                "code=" + code +
                "message"+this.getMessage()+
                '}';
    }
}
