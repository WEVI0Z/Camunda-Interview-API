package by.wevioz.delegate;

import by.wevioz.service.NotifyService;
import by.wevioz.service.NotifyServiceAdminReminder;
import by.wevioz.service.NotifyServiceIntervieweeReminder;
import by.wevioz.service.NotifyServiceSalesReminder;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Slf4j
public class InterviewStillInProgressMailDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        NotifyService notifyService = new NotifyService();
        notifyService = new NotifyServiceIntervieweeReminder(notifyService);
        notifyService = new NotifyServiceSalesReminder(notifyService);
        notifyService = new NotifyServiceAdminReminder(notifyService);
        notifyService.sendMessage("Interview process " + delegateExecution.getProcessDefinitionId() + " still in progress");

        log.info("Executed the service task: " + delegateExecution.getCurrentActivityName());
    }
}
