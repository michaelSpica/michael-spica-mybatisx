package michael.spica.mybatisx.common.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import michael.spica.mybatisx.common.constant.HeaderConstants;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by michael on 2025-11-21.
 */
@Slf4j
@Component
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 生成或读取 TraceId
        String traceId = request.getHeader(HeaderConstants.TRACE_ID);
        if (StringUtils.isBlank(traceId)) {
            traceId = UUID.randomUUID().toString().replace("-", "");
        }

        MDC.put(HeaderConstants.TRACE_ID, traceId);
        response.setHeader(HeaderConstants.TRACE_ID, traceId);
        long start = System.currentTimeMillis();
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String query = request.getQueryString();
        log.info("请求开始 => traceId={}, ip={}, method={}, uri={}, query={}", traceId, getClientIP(request), method, uri, query);
        try {
            // 若需记录请求体，可用 ContentCachingRequestWrapper（注意生产脱敏）
            filterChain.doFilter(request, response);
        } finally {
            long duration = System.currentTimeMillis() - start;
            log.info("请求结束 => traceId={}, uri={}, status={}, 耗时:{}ms", traceId, uri, response.getStatus(), duration);
            MDC.remove("traceId");
        }
    }

    private String getClientIP(HttpServletRequest request) {
        String xf = request.getHeader(HeaderConstants.FORWARDED_FOR);
        if (StringUtils.isNotBlank(xf)) {
            return xf.split(",")[0];
        }
        return request.getRemoteAddr();
    }
}
