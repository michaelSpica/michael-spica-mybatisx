package michael.spica.mybatisx.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 公用状态
 * <p>
 * Created by michael on 2025-10-23.
 */
@Getter
@AllArgsConstructor
public enum CommonStatus {

    ENABLED("启用"),

    DISABLED("禁用");

    private final String label;
}
