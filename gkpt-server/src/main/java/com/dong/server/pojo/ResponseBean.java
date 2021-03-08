package com.dong.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: BaseBean
 * @Author: 努力努力再努力d
 * @Description: 公共返回对象
 * @Date: 2021/3/5 0:29
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBean {
    private long code;
    private String message;
    private Object obj;
    public static ResponseBean success(String message){
        return new ResponseBean(200,message,null);
    }

    public static ResponseBean success(String message,Object obj){
        return new ResponseBean(200,message,obj);
    }

    public static ResponseBean error(String message){
        return new ResponseBean(500,message,null);
    }

    public static ResponseBean error(String message,Object obj){
        return new ResponseBean(500,message,obj);
    }
}
