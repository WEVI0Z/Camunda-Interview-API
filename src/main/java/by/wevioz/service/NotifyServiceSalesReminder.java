package by.wevioz.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotifyServiceSalesReminder extends NotifyServiceWrapper {
    public NotifyServiceSalesReminder(NotifyService notifyService) {
        super(notifyService);
    }

    @Override
    public void sendMessage(String message) {
        super.sendMessage(message);
        log.info("Message for sales manager sent. The message: " + message);
    }
}
