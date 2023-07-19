package by.wevioz.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Slf4j
public class InterviewValidationDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String interviewData = (String) delegateExecution.getVariable("interviewData");

        boolean valid = interviewData != null;

        delegateExecution.setVariable("interviewValid", valid);

        log.info("Executed the service task: " + delegateExecution.getCurrentActivityName());
    }
}
