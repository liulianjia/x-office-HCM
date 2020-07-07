package entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * entity:MQ消息封装
 */
public class Message implements Serializable {

//    执行的操作 1：增加 2：修改 3：删除
    private int code;

//    数据
    private Object content;

//    发送的routekey
    @JSONField(serialize = false)
    private String routeKey;

//    交换机
    @JSONField(serialize = false)
    private String exechange;

    public Message() {

    }

    public Message(int code,Object content) {
        this.code = code;
        this.content = content;
    }
}
