package com.aochong.for52pojie.handler;

import com.aochong.for52pojie.config.UserConfig;
import com.aochong.for52pojie.enums.ResultMacthEnum;
import com.aochong.for52pojie.enums.UrlEnum;
import com.aochong.for52pojie.repository.ReplayLogRepository;
import com.aochong.for52pojie.service.ReplayLogService;
import com.aochong.for52pojie.util.ListUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhangaochong
 * @date 2019-03-02 13:02
 */
@Component
@Slf4j
public class ReplayHandler {
    @Autowired
    private UserConfig userConfig;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ReplayLogService replayLogService;
    @Autowired
    private ReplayLogRepository replayLogRepository;

    /**
     * 回帖
     *
     * @return
     */
    public boolean replay() {
        // 请求体
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("formhash", userConfig.getFormHash());
        // 随机获取回帖内容
        String content = ListUtils.randomGet(userConfig.getMessageList());
        multiValueMap.add("message", content);

        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Cookie", userConfig.getCookie());

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(multiValueMap, headers);

        // 获取帖子列表
        List<Long> tidList = getTidList(UrlEnum.SOFTWARE_AREA.getValue());
        for (Long tid : tidList) {

            // 未回过该帖
            if (!replayLogRepository.findById(tid).isPresent()) {
                // 执行回帖
                String replayResult = restTemplate.postForObject(UrlEnum.REPLY.getValue() + tid, httpEntity, String.class);

                // 回帖成功
                if (replayResult.contains(ResultMacthEnum.SUCCESS.getValue())) {
                    // 回帖记录添加到数据库
                    replayLogService.add(tid);
                    log.info("[回帖成功] tid={} 内容={}", tid, content);
                    return true;
                }

                // 回帖失败, 重新回下一帖
                if (replayResult.contains(ResultMacthEnum.NOT_EXIST.getValue())) {
                    log.info("[回帖失败,重回] tid={} 内容={} 失败信息={}", tid, content, ResultMacthEnum.NOT_EXIST.getValue());
                    continue;
                }

                // 回帖失败
                log.error("[回帖失败] tid={} 内容={} 失败信息={}", tid, content, replayResult);
                return false;
            }

            // 已回过该帖
            log.info("[丢弃重复帖] tid={}", tid);
        }
        // 无可回复帖
        log.error("[无可回复帖] 帖子列表={}", tidList);
        return false;
    }

    /**
     * 获取帖子列表
     *
     * @param url
     * @return
     */
    private List<Long> getTidList(String url) {
        // 获取html
        String html = restTemplate.getForObject(url, String.class);
        // 正则匹配帖子ID
        String pattern = "id=\"normalthread_(\\d{6})";
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(html);

        List<Long> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(Long.parseLong(matcher.group(1)));
        }
        log.info("[帖子列表] {}", list);
        return list;
    }
}
