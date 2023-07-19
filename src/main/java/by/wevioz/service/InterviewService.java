package by.wevioz.service;

import by.wevioz.dto.InterviewDto;
import by.wevioz.enumeration.TaskIdEnum;
import by.wevioz.exception.NotFoundException;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InterviewService {
    private final String MAIN_PROCESS_NAME = "Interview_lifecycle_process";
    private final RuntimeService runtimeService;
    private final TaskService taskService;

    @Autowired
    public InterviewService(
            RuntimeService runtimeService,
            TaskService taskService
    ) {
        this.runtimeService = runtimeService;
        this.taskService = taskService;
    }

    public InterviewDto startProcess() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(MAIN_PROCESS_NAME);

        String instanceId = processInstance.getId();

        return new InterviewDto(instanceId);
    }

    public InterviewDto sendInterview(InterviewDto interviewDto) {
        checkIfProcessExists(interviewDto.getInstanceId());

        boolean revalidation = !(runtimeService.getVariable(interviewDto.getInstanceId(), "interviewValid") == null);

        Task task = revalidation ?
                findTask(interviewDto.getInstanceId(), TaskIdEnum.TRIGGER_INTERVIEW_REVALIDATION.getTitle()) :
                findTask(interviewDto.getInstanceId(), TaskIdEnum.RESOLVE_INTERVIEW.getTitle());

        return getInterviewDto(interviewDto, task);
    }

    public InterviewDto addStep(InterviewDto interviewDto) {
        checkIfProcessExists(interviewDto.getInstanceId());

        Task task = findTask(interviewDto.getInstanceId(), TaskIdEnum.CREATE_STEP.getTitle());

        return getInterviewDto(interviewDto, task);
    }

    private InterviewDto getInterviewDto(InterviewDto interviewDto, Task task) {
        Map<String, Object> variables = new HashMap<>();

        variables.put("interviewData", interviewDto.getInterviewData());

        taskService.complete(task.getId(), variables);

        String status = getCurrentActivityStatus(interviewDto.getInstanceId());

        interviewDto.setStatus(status);

        return interviewDto;
    }

    private boolean checkIfProcessExists(String processInstanceId) {
        if (runtimeService.getActivityInstance(processInstanceId) == null) {
            throw new NotFoundException("process");
        }

        return true;
    }

    private String getCurrentActivityStatus(String processInstanceId) {
        String status = "DONE";

        if (runtimeService.getActivityInstance(processInstanceId) != null)
            status = (String) runtimeService.getVariable(processInstanceId, "status");

        return status;
    }

    private Task findTask(String processInstanceId, String taskId) {
        List<Task> tasks = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .list();

        Optional<Task> task = tasks
                .stream()
                .filter(currentTask -> currentTask.getTaskDefinitionKey().equals(taskId))
                .findFirst();

        if (task.isEmpty()) {
            throw new NotFoundException("task");
        }

        return task.get();
    }
}
