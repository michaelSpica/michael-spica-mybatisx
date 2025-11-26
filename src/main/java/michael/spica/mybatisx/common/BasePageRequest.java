package michael.spica.mybatisx.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by michael on 2025-11-25.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasePageRequest {

    /**
     * 页码
     */
    private Long current;

    /**
     * 每页大小
     */
    private Long size;

    /**
     * 排序字段（选填）
     */
    private String orderBy;

    /**
     * 是否升序，默认 false=降序
     */
    private boolean asc = false;

    public long getCurrent() {
        return current != null && current > 0 ? current : 1L;// 默认第一页
    }

    public long getSize() {
        if (null == size || size <= 0) {
            return 10L;// 默认每页10条数据
        }
        return Math.min(size, 1000L); // 安全上限
    }
}
