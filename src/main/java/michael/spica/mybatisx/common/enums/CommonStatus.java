package michael.spica.mybatisx.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import michael.spica.mybatisx.common.exception.BizException;

import java.util.Arrays;

/**
 * 公用状态
 * <p>
 * Created by michael on 2025-10-23.
 */
@Getter
@AllArgsConstructor
public enum CommonStatus {

    ENABLED("enabled", "启用"),

    DISABLED("disabled", "禁用");

    private final String value;

    private final String label;

    public static CommonStatus from(String status) {
        return Arrays.stream(values())
                .filter(a -> a.value.equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() -> new BizException(ResultCode.INVALID_OPERATE_TYPE));
    }
}
