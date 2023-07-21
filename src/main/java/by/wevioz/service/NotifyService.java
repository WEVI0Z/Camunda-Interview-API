package by.wevioz.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotifyService {
    public void sendMessage(String message) {
      log.info("Standard message sent. The message: " + message);
    }
}
