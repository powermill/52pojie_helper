package com.aochong.for52pojie.enums;

import lombok.Getter;

/**
 * URL枚举
 *
 * @author zhangaochong
 * @date 2019-03-02 13:09
 */
@Getter
public enum UrlEnum {
    /** 回帖 */
    REPLY("回帖", "https://www.52pojie.cn/forum.php?mod=post&action=reply&fid=16&extra=page=1&replysubmit=yes&mobile=2&handlekey=fastpost&loc=1&inajax=1&tid="),
    /** 精品软件区 */
    SOFTWARE_AREA("精品软件区", "https://www.52pojie.cn/forum-16-1.html"),
    ;

    private String name;
    private String value;

    UrlEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
