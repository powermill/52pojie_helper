package com.aochong.for52pojie.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户配置
 *
 * @author zhangaochong
 * @date 2019-03-02 12:23
 */
@Component
@ConfigurationProperties(prefix = "userconfig")
@Data
public class UserConfig {
    private String cookie;
    private String formHash;
    private List<String> messageList;
}
