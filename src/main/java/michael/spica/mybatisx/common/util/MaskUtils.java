package michael.spica.mybatisx.common.util;

/**
 * Created by michael on 2025-11-21.
 */
public class MaskUtils {

    public static String maskMobile(String mobile) {
        if (mobile == null || mobile.length() < 7) {
            return mobile;
        }
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    public static String maskSensitive(String json) {
        // 简单示例：对手机号、身份证、银行卡进行正则替换
        String s = json;
        s = s.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        // 更多规则根据项目扩展
        return s;
    }
}
