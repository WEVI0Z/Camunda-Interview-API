package by.wevioz.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotifyServiceIntervieweeReminder extends NotifyServiceWrapper{
    public NotifyServiceIntervieweeReminder(NotifyService notifyService) {
        super(notifyService);
    }

    @Override
    public void sendMessage(String message) {
        super.sendMessage(message);
        log.info("Message for interviewee sent. The message: " + message);
    }
}
