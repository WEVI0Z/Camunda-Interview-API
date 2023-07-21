package by.wevioz.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotifyServiceAdminReminder extends NotifyServiceWrapper {
    public NotifyServiceAdminReminder(NotifyService notifyService) {
        super(notifyService);
    }

    @Override
    public void sendMessage(String message) {
        super.sendMessage(message);

        log.info("Message for admin sent. The message: " + message);
    }
}
