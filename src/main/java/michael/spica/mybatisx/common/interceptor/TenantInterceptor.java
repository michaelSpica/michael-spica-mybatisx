package michael.spica.mybatisx.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import michael.spica.mybatisx.common.constant.HeaderConstants;
import michael.spica.mybatisx.common.context.TenantContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Created by michael on 2025-11-20.
 */
@Component
public class TenantInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 例如从 JWT token 或 header 中解析 tenantId
        String tenantIdStr = request.getHeader(HeaderConstants.TENANT_ID);
        if (tenantIdStr != null) {
            Long tenantId = Long.valueOf(tenantIdStr);
            TenantContext.setCurrentTenantId(tenantId);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        TenantContext.clear(); // 清理 ThreadLocal，防止内存泄漏
    }
}
