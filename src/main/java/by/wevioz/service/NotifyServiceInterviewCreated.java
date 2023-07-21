package by.wevioz.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotifyServiceInterviewCreated extends NotifyServiceWrapper{
    public NotifyServiceInterviewCreated(NotifyService notifyService) {
        super(notifyService);
    }

    @Override
    public void sendMessage(String message) {
        super.sendMessage(message);
        log.info("Interview created message sent. The message: " + message);
    }
}
