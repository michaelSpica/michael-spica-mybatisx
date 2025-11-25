package michael.spica.mybatisx.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * Created by michael on 2025-11-21.
 */
@Data
@NoArgsConstructor
public class R<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 8135796852402304364L;

    private int code;    // 状态码（200 成功，非200 失败）

    private String msg;  // 消息

    private T data;      // 数据

    public static <T> R<T> ok() {
        return restResult(null, 200, "操作成功");
    }

    public static <T> R<T> ok(T data) {
        return restResult(data, 200, "操作成功");
    }

    public static <T> R<T> ok(String msg) {
        return restResult(null, 200, msg);
    }

    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, 200, msg);
    }

    public static <T> R<T> fail() {
        return restResult(null, 500, "操作失败");
    }

    public static <T> R<T> fail(String msg) {
        return restResult(null, 500, msg);
    }

    public static <T> R<T> fail(int code, String msg) {
        return restResult(null, code, msg);
    }

    public static <T> R<T> fail(T data, String msg) {
        return restResult(data, 500, msg);
    }

    private static <T> R<T> restResult(T data, int code, String msg) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
