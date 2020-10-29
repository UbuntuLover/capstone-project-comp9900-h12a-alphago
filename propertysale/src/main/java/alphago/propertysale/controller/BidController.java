package alphago.propertysale.controller;

import alphago.propertysale.entity.Rab;
import alphago.propertysale.entity.RabAction;
import alphago.propertysale.service.RabActionService;
import alphago.propertysale.shiro.JwtInfo;
import alphago.propertysale.utils.Result;
import alphago.propertysale.websocket.BidHistoryPush;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @program: propertysale
 * @description:
 * @author: XIAO HAN
 * @create: 2020-10-17 13:18
 **/
@RestController
@RequestMapping("/bid")
public class BidController {
    @Autowired
    RabActionService rabActionService;

    @RequiresAuthentication
    public Result bid(RabAction rabAction) {
        Subject subject = SecurityUtils.getSubject();
        JwtInfo info = (JwtInfo) subject.getPrincipal();
        long uid = info.getUid();

        rabAction.setBidTime(LocalDateTime.now());
        rabActionService.bid(rabAction);
        BidHistoryPush.bidPush(String.valueOf(rabAction.getActionId()), rabAction);
        return Result.success(rabAction);
    }
}