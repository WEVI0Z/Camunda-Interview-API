package by.wevioz.delegate;

import by.wevioz.service.NotifyService;
import by.wevioz.service.NotifyServiceInterviewCreated;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Slf4j
public class InterviewCreatedMailDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        NotifyService notifyService = new NotifyService();
        notifyService = new NotifyServiceInterviewCreated(notifyService);
        notifyService.sendMessage("Interview process " + delegateExecution.getProcessDefinitionId() + " initialized");

        log.info("Executed the service task: " + delegateExecution.getCurrentActivityName());
    }
}
