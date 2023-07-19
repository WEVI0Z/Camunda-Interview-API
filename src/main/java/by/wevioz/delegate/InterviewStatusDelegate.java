package by.wevioz.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class InterviewStatusDelegate implements JavaDelegate {
    final String IN_PROGRESS_ACTIVITY_ID = "Activity_1whkss2";
    final String REVIEW_ACTIVITY_ID = "Activity_10753a3";
    final String DONE_ACTIVITY_ID = "Activity_0kxp5e8";

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        switch (delegateExecution.getCurrentActivityId()) {
            case (IN_PROGRESS_ACTIVITY_ID) -> delegateExecution.setVariable("status", "in progress");
            case (REVIEW_ACTIVITY_ID) -> delegateExecution.setVariable("status", "review");
            case (DONE_ACTIVITY_ID) -> delegateExecution.setVariable("status", "done");
        }

        System.out.println(this.toString());
    }
}
