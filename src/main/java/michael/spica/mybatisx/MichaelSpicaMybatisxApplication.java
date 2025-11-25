package michael.spica.mybatisx;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
@MapperScan("michael.spica.mybatisx.mapper")// 👈 扫描 Mapper 接口所在包
public class MichaelSpicaMybatisxApplication {

    private static final String SPRING_APPLICATION_NAME = "spring.application.name";
    private static final String SPRING_APPLICATION_ACTIVE = "spring.profiles.active";
    private static final String SERVER_PORT = "server.port";

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MichaelSpicaMybatisxApplication.class);
        Environment env = app.run(args).getEnvironment();
        String applicationName = env.getProperty(SPRING_APPLICATION_NAME);
        String serverPort = env.getProperty(SERVER_PORT);
        String configServerStatus = env.getProperty(SPRING_APPLICATION_ACTIVE);

        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            log.info("\n---------------------------------------------------------------------------------\n\t" +
                            "Application '{}' is running! Access URLs:\n\t" +
                            "Local: \t\thttp://localhost:{}\n\t" +
                            "External: \thttp://{}:{}\n---------------------------------------------------------------------------------",
                    applicationName,
                    serverPort,
                    hostAddress, serverPort);

            log.info("\n---------------------------------------------------------------------------------\n\t" +
                            "Config Server: \t{}\n---------------------------------------------------------------------------------",
                    configServerStatus == null ? "Not found or not setup for this application" : configServerStatus);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
