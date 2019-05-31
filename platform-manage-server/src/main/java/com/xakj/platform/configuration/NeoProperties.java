package com.xakj.platform.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 用来接收多个配置项参数
 * 如果测试中出现中文乱码，可按照以下方法进行设置：
 *
 * 依次单击 File | Settings | Editor | File Encodings 命令，将 Properties Files (*.properties) 下的 Default encoding for properties files 设置为 UTF-8，勾选 Transparent native-to-ascii conversion 复选框。
 */
@Component
@ConfigurationProperties(prefix = "neo")
//@PropertySource("classpath:other.properties") 自定义的配置文件 加个注解指明配置文件地址
public class NeoProperties {
    private String title;
    private String description;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title+"w泡泡堂";
    }

    public String getDescription() {
        return description;
    }
}
