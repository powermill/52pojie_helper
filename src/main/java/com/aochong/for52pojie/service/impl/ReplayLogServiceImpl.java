package com.aochong.for52pojie.service.impl;

import com.aochong.for52pojie.entity.ReplayLog;
import com.aochong.for52pojie.repository.ReplayLogRepository;
import com.aochong.for52pojie.service.ReplayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zhangaochong
 * @date 2019-03-02 14:00
 */
@Service
public class ReplayLogServiceImpl implements ReplayLogService {
    @Autowired
    private ReplayLogRepository replayLogRepository;

    @Override
    public void add(Long tid) {
        ReplayLog replayLog = new ReplayLog();
        replayLog.setTid(tid);
        replayLog.setReplayDate(new Date());
        replayLogRepository.save(replayLog);
    }
}
