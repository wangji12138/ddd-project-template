package cn.changemax.ddd.infrastructure.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author WangJi
 * @Description cm项目配置文件
 * @Date 2020/6/11 18:11
 */
@Data
@Component
@ConfigurationProperties(prefix = "cm-properties")
public class CmProperties {

    /**
     * swagger配置
     */
    private SwaggerProperties swagger = new SwaggerProperties();

    @Data
    @NoArgsConstructor
    public static class SwaggerProperties {
        /**
         * 是否开启
         */
        private boolean enabled = false;
        /**
         * 组名
         */
        private String groupName = "changemax v1.0.0 API";
        /**
         * 父包名
         */
        private String basePackage = "cn.changemax.interfaces.facade";
        /**
         * swagger标题
         */
        private String title = "ChangeMax Ecology Swagger Restful API.";
        /**
         * 简介
         */
        private String description = "ChangeMax Ecology API Description";
        /**
         * 版本
         */
        private String version = "1.0.0";
        /**
         * 作者
         */
        private String author = "ChangeMax-WJ";
        /**
         * 地址
         */
        private String url = "changemax.cn";
        /**
         * 邮箱
         */
        private String email = "changemaxwj@163.com";
        /**
         * 许可证
         */
        private String license = "Apache 2.0";
        /**
         * 许可证url
         */
        private String licenseUrl = "https://www.apache.org/licenses/LICENSE-2.0.html";
    }
}
