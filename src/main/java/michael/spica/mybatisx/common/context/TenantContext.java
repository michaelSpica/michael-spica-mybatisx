package michael.spica.mybatisx.common.context;

/**
 * Created by michael on 2025-11-20.
 */
public class TenantContext {

    private static final ThreadLocal<Long> CURRENT_TENANT_ID = new ThreadLocal<>();

    public static void setCurrentTenantId(Long tenantId) {
        CURRENT_TENANT_ID.set(tenantId);
    }

    public static Long getCurrentTenantId() {
        return CURRENT_TENANT_ID.get();
    }

    public static void clear() {
        CURRENT_TENANT_ID.remove();
    }
}
