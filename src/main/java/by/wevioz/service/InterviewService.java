package by.wevioz.service;

import by.wevioz.dto.InterviewDto;
import by.wevioz.enumeration.TaskIdEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        Task task = findTask(interviewDto.getInstanceId(), TaskIdEnum.RESOLVE_INTERVIEW.getTitle());

        taskService.complete(task.getId());

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
