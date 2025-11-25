package michael.spica.mybatisx.common;

import lombok.Data;

/**
 * Created by michael on 2025-11-25.
 */
@Data
public class PageRequest {

    /**
     * 页码
     */
    private Long current;

    /**
     * 每页大小
     */
    private Long size;

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
