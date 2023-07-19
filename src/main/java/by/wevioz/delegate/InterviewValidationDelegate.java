package by.wevioz.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class InterviewValidationDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String interviewData = (String) delegateExecution.getVariable("interviewData");

        boolean valid = interviewData != null;

        delegateExecution.setVariable("interviewValid", valid);

        System.out.println(this.toString());
    }
}
