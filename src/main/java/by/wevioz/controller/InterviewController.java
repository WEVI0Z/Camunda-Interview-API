package by.wevioz.controller;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interview")
public class InterviewController {
    @Autowired
    private RuntimeService runtimeService;

    @PostMapping
    public ResponseEntity startProcess() {
//        runtimeService.startProcessInstanceById("Interview_lifecycle_process");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Interview_lifecycle_process");
        return ResponseEntity.ok("Process started with instance ID: " + processInstance.getId());
    }
}
