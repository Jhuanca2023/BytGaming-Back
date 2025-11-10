package gaming.pe.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.net.URI;

/**
 * Configuración de DataSource que soporta tanto Render (PostgreSQL) como Railway/MySQL
 * Render proporciona DATABASE_URL en formato: postgresql://user:pass@host:port/db
 * Railway/MySQL usa DB_URL en formato JDBC: jdbc:mysql://host:port/db
 * 
 * Si DATABASE_URL está presente y NO es JDBC (Render), convierte automáticamente a formato JDBC
 * Si solo DB_URL está presente (Railway/MySQL), Spring Boot usa la configuración automática
 */
@Configuration
public class DatabaseConfig {

    /**
     * Configura el DataSource cuando DATABASE_URL está presente y NO es JDBC (Render)
     * Convierte automáticamente la URL de Render a formato JDBC
     * Esta configuración solo se activa cuando DATABASE_URL existe y no comienza con "jdbc:"
     */
    @Bean
    @Primary
    @ConditionalOnExpression("T(java.lang.System).getenv('DATABASE_URL') != null && !T(java.lang.System).getenv('DATABASE_URL').startsWith('jdbc:')")
    public DataSource renderDataSource() {
        String databaseUrl = System.getenv("DATABASE_URL");

        try {
            // Convertir URL de Render a formato JDBC
            URI uri = new URI(databaseUrl);
            String scheme = uri.getScheme();
            String host = uri.getHost();
            int port = uri.getPort() > 0 ? uri.getPort() : getDefaultPort(scheme);
            String path = uri.getPath();
            String userInfo = uri.getUserInfo();

            // Construir URL JDBC
            String jdbcUrl = buildJdbcUrl(scheme, host, port, path);
            
            DataSourceBuilder<?> builder = DataSourceBuilder.create();
            builder.url(jdbcUrl);

            // Extraer usuario y contraseña de la URL si están presentes
            if (userInfo != null && userInfo.contains(":")) {
                String[] userPass = userInfo.split(":", 2);
                builder.username(userPass[0]);
                if (userPass.length > 1) {
                    builder.password(userPass[1]);
                }
            } else {
                // Usar variables de entorno si están disponibles
                String username = System.getenv("DATABASE_USERNAME");
                if (username != null && !username.isEmpty()) {
                    builder.username(username);
                }
                
                String password = System.getenv("DATABASE_PASSWORD");
                if (password != null && !password.isEmpty()) {
                    builder.password(password);
                }
            }

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir DATABASE_URL: " + databaseUrl, e);
        }
    }

    private int getDefaultPort(String scheme) {
        if ("postgresql".equals(scheme) || "postgres".equals(scheme)) {
            return 5432;
        } else if ("mysql".equals(scheme)) {
            return 3306;
        }
        return 5432;
    }

    private String buildJdbcUrl(String scheme, String host, int port, String path) {
        String dbName = path.startsWith("/") ? path.substring(1) : path;

        if ("postgresql".equals(scheme) || "postgres".equals(scheme)) {
            return String.format("jdbc:postgresql://%s:%d/%s", host, port, dbName);
        } else if ("mysql".equals(scheme)) {
            return String.format("jdbc:mysql://%s:%d/%s?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", host, port, dbName);
        }
        return String.format("jdbc:postgresql://%s:%d/%s", host, port, dbName);
    }
}

