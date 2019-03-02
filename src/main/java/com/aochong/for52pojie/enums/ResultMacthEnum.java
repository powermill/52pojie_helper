package com.aochong.for52pojie.enums;

import lombok.Getter;

/**
 * 回帖返回值匹配枚举
 *
 * @author zhangaochong
 * @date 2019-03-02 13:14
 */
@Getter
public enum  ResultMacthEnum {
    /** 回复成功 */
    SUCCESS("回复成功", "回复发布成功"),
    /** 帖子不存在 */
    NOT_EXIST("帖子不存在", "指定的主题不存在或已被删除或正在被审核"),
    ;

    private String name;
    private String value;

    ResultMacthEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
