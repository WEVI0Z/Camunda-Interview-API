package by.wevioz.delegate;

import by.wevioz.service.NotifyService;
import by.wevioz.service.NotifyServiceIntervieweeReminder;
import by.wevioz.service.NotifyServiceSalesReminder;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Slf4j
public class InterviewStepSucceededMailDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        NotifyService notifyService = new NotifyService();
        notifyService = new NotifyServiceIntervieweeReminder(notifyService);
        notifyService.sendMessage("Interview process " + delegateExecution.getProcessDefinitionId() + ": previous step succeed");

        log.info("Executed the service task: " + delegateExecution.getCurrentActivityName());
    }
}
