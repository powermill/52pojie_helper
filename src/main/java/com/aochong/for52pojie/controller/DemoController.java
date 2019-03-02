package com.aochong.for52pojie.controller;

import com.aochong.for52pojie.config.UserConfig;
import com.aochong.for52pojie.entity.ReplayLog;
import com.aochong.for52pojie.repository.ReplayLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author zhangaochong
 * @date 2019-03-02 11:20
 */
@RestController
public class DemoController {
    @Autowired
    private ReplayLogRepository replayLogRepository;
    @Autowired
    private UserConfig userConfig;

    @GetMapping("/add")
    public String add(Long tid) {
        ReplayLog replayLog = new ReplayLog();
        replayLog.setTid(tid);
        replayLog.setReplayDate(new Date());
        replayLogRepository.save(replayLog);
        return "ok";
    }

    @GetMapping("/list")
    public List<ReplayLog> list() {
        return replayLogRepository.findAll();
    }

    @GetMapping("config")
    public UserConfig config() {
        System.out.println(userConfig);
        return userConfig;
    }
}
