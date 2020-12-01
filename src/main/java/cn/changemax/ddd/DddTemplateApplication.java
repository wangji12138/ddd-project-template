package cn.changemax.ddd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;

/**
 * @author WangJi
 */
@Slf4j
@SpringBootApplication
@EnableAsync//开启异步调用
@EnableScheduling//开启定时任务
public class DddTemplateApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(DddTemplateApplication.class, args);

        try {
            Environment env = application.getEnvironment();
            Runtime runtime = Runtime.getRuntime();
            log.info("\n----------------------------------------------------------\n\t" +
                            "Application '{}' v{} is running! URLs:\n\t" +
                            "Doc: \thttp://{}:{}/swagger-ui.html\n\t" +
                            "FREE_MEMORY: \t{}MB\n\t" + //jvm空闲内存
                            "TOTAL_MEMORY: \t{}MB\n\t" + //jvm总内存 默认物理内存1/64
                            "MAX_MEMORY: \t{}MB\n" + //jvm最大可用内存 默认 物理内存1/4
//                        "MSS_STACK: \t{}\n" + //栈内存量 默认 1024k
                            "----------------------------------------------------------",
                    env.getProperty("spring.application.name"), env.getProperty("xh-properties.version"),
                    InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"),
                    runtime.freeMemory() / 1024.0 / 1024, runtime.totalMemory() / 1024.0 / 1024, runtime.maxMemory() / 1024.0 / 1024);
        } catch (Exception e) {
            log.error("打印启动信息异常：{}", e.getMessage());
        }

    }
}
