package entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Result", value = "Result")
public class Result<T> {
    /**
     * 返回体
     */
    @ApiModelProperty(value = "返回状态码", required = true)
    private Integer code;//返回状态码

    @ApiModelProperty(value = "提示消息", required = true)
    private String msg;//返回消息

    @ApiModelProperty(value = "逻辑数据", required = true)
    private T data;//返回数据

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }



    /**
     * 成功，且返回体有数据
     * @return
     */
    public static Result success(Integer code, String msg, Object object) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(object);

        return result;
    }

    /**
     * 成功，但返回体无数据
     */
    public static Result success(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);

        return result;
    }

    /**
     * 返回失败信息
     */
    public static Result Err(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return  result;
    }
}
