package by.wevioz.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class NotifyServiceWrapper extends NotifyService {
    private final NotifyService notifyService;

    @Override
    public void sendMessage(String message) {
        notifyService.sendMessage(message);
    }
}
