package michael.spica.mybatisx.config;

import jakarta.annotation.PostConstruct;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;

/**
 * Created by michael on 2025-11-20.
 */
@Component
public class TablePrefixNamingStrategy implements PhysicalNamingStrategy {

    @Value("${spring.jpa.hibernate.naming.table-prefix:}")
    private String tablePrefix;

    @Value("${spring.jpa.hibernate.naming.prefix-enabled:false}")
    private boolean prefixEnabled;

    private String effectivePrefix = "";// 表有效前缀

    @PostConstruct
    public void init() {
        effectivePrefix = Optional.ofNullable(tablePrefix)
                .map(String::trim)
                .filter(s -> !s.isEmpty() && prefixEnabled)
//                .map(s -> s + "_")  // 如果后面拼接表名时不需要再加 "_"，可去掉这里
                .orElse("");
    }

    @Override
    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.apply(identifier, jdbcEnvironment);
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.apply(identifier, jdbcEnvironment);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        if (null == identifier) {
            return null;
        }

        String logicalName = identifier.getText();
        String snakeCaseName = this.convertToSnakeCase(logicalName);
        String physicalName = prefixEnabled && !effectivePrefix.isEmpty() ? effectivePrefix + snakeCaseName : snakeCaseName;
        return Identifier.toIdentifier(physicalName, identifier.isQuoted());
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.apply(identifier, jdbcEnvironment);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.apply(identifier, jdbcEnvironment);
    }

    /**
     * 将驼峰命名转换为下划线命名
     *
     * @param identifier
     * @param jdbcEnvironment
     * @return
     */
    private Identifier apply(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        if (null == identifier) {
            return null;
        }
        String text = identifier.getText();
        String convertedName = this.convertToSnakeCase(text);
        return Identifier.toIdentifier(convertedName, identifier.isQuoted());
    }

    /**
     * 驼峰转下划线（简单正则替换）
     *
     * @param name
     * @return
     */
    private String convertToSnakeCase(String name) {
        if (null == name) {
            return null;
        }
        // 先处理大写字母前加下划线，然后转小写
        return name.replaceAll("([a-z0-9])([A-Z])", "$1_$2").toLowerCase(Locale.ROOT);

    }
}
