package cn.beerate.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "响应数据")
@Data
public class Message<T> {

    @ApiModelProperty(value = "状态码(200：成功,-1：失败)")
    private int code;

    @ApiModelProperty(value = "响应信息")
    private String msg;

    @ApiModelProperty(value = "响应数据")
    private T data;

    public Message(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Message(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Message<String> ok(){
        return new Message<>(StatusCode.SUCCESS,"success");
    }

    public static Message<String> ok(String msg){
        return new Message<>(StatusCode.SUCCESS,msg);
    }

    public static <T> Message<T> success(T data){
        return new Message<>(StatusCode.SUCCESS,"success",data);
    }

    public static <T> Message<T> error(){
        return new Message<>(StatusCode.ERROR,"error");
    }

    public static <T> Message<T> error(String msg){
        return new Message<>(StatusCode.ERROR,msg);
    }

    public boolean fail(){
        return this.code== StatusCode.ERROR;
    }
}
