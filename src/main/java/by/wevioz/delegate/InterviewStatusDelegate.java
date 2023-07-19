package by.wevioz.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class InterviewStatusDelegate implements JavaDelegate {
    final String IN_PROGRESS_ACTIVITY_ID = "Activity_1whkss2";
    final String REVIEW_ACTIVITY_ID = "Activity_10753a3";
    final String DONE_ACTIVITY_ID = "Activity_0kxp5e8";

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println(this.toString());
    }
}
