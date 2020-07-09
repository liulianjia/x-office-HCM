package entity;

/**
 * 响应状态返回码
 */
public class StatusCode {
    public static final Integer OK = 200; //正确
    public static final Integer BAD_REQUEST = 400; //错误的请求
    public static final Integer UNAUTHORIZED = 401; //禁止访问
    public static final Integer NOT_FOUND = 404; //没有可用的数据
    public static final Integer PWD_ERROR = 300; //密码错误
    public static final Integer EXIT = 403; //已经存在
    public static final Integer INTERNAL_SERVER_ERROR = 500; //服务器遇到一个未曾预料的状况
    public static final Integer SERVICE_UNAVAILABLE = 503; //服务器当前无法处理请求
    public static final Integer ERROR = 9999; //数据不能为空
}
