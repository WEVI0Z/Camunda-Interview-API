package by.wevioz.service;

import by.wevioz.dto.InterviewDto;
import by.wevioz.enumeration.TaskIdEnum;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InterviewService {
    List<String> instancesIds = new ArrayList<>();
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
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Interview_lifecycle_process");

        String instanceId = processInstance.getId();

        instancesIds.add(instanceId);

        return new InterviewDto(instanceId);
    }

    public InterviewDto sendInterview(InterviewDto interviewDto) {
        boolean revalidation = !(runtimeService.getVariable(interviewDto.getInstanceId(), "interviewValid") == null);

        Task task = revalidation ?
                findTask(interviewDto.getInstanceId(), TaskIdEnum.TRIGGER_INTERVIEW_REVALIDATION.getTitle()) :
                findTask(interviewDto.getInstanceId(), TaskIdEnum.RESOLVE_INTERVIEW.getTitle());

        Map<String, Object> variables = new HashMap<>();

        variables.put("interviewData", interviewDto.getInterviewData());

        taskService.complete(task.getId(), variables);

        return interviewDto;
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
            throw new RuntimeException();
        }

        return task.get();
    }
}
