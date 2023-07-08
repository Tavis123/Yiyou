package com.work.controller;

import com.work.pojo.ResultInfo;
import com.work.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/checkIsFirstChat")
    public ResultInfo checkIsFirstChat(@RequestParam String fromUser, @RequestParam String toUser) {

        boolean flag = chatService.isFirstChat(fromUser, toUser);

        if (flag) {
            return ResultInfo.successInfo("第一次进行聊天");
        } else {
            return ResultInfo.failInfo("不是第一次进行聊天");
        }
    }


    @GetMapping("/getChatList")
    public ResultInfo chatList(@RequestParam String fromUser) {
        //获取当前用户的聊天列表返回
        return chatService.getFromUserChatList(fromUser);
    }

    @GetMapping("/getChatRecords")
    public ResultInfo recentChatRecords(@RequestParam String toUser, @RequestParam int startIndex, @RequestParam int pageSize, @RequestParam String fromUser) {

        //获取最近的聊天记录
        return chatService.getRecentChatRecords(fromUser, toUser, startIndex, pageSize);

    }

    @GetMapping("/getPageNumber")
    public ResultInfo chatRecordsPageNumber(String linkId) {

        return chatService.getPageNumber(linkId);
    }

    @GetMapping("/inWindows")
    public void updateIsSaveWindows(@RequestParam String toUser, @RequestParam String fromUser) {
        chatService.updateWindows(fromUser, toUser);
    }

    @GetMapping("/unread")
    public ResultInfo unreadTotalNumber(@RequestParam String userName) {
        return chatService.getUnreadTotalNumber(userName);

    }

    @GetMapping("/resetWindows")
    public ResultInfo resetWindows(@RequestParam String username) {
        chatService.resetWindows(username);
        return ResultInfo.successInfo("重置成功！");
    }

}
