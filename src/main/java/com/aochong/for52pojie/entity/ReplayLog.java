package com.aochong.for52pojie.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 已回过的帖记录
 *
 * @author zhangaochong
 * @date 2019-03-02 10:49
 */
@Entity
@Data
public class ReplayLog {
    /** 帖子ID */
    @Id
    private Long tid;
    /** 回帖时间 */
    private Date replayDate;
}
