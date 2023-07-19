package by.wevioz.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Slf4j
public class InterviewTimerAlreadyExistsCheckDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.setVariable("timerExists", false);

        log.info("Executed the service task: " + delegateExecution.getCurrentActivityName());
    }
}
