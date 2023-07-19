package by.wevioz.controller;

import by.wevioz.dto.InterviewDto;
import by.wevioz.service.InterviewService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/interview")
public class InterviewController {
    private final InterviewService service;

    public InterviewController(InterviewService interviewService) {
        service = interviewService;
    }

    @PostMapping
    public InterviewDto startProcess() {
        return service.startProcess();
    }

    @PostMapping("/send")
    public InterviewDto sendInterview(@RequestBody @Valid InterviewDto interviewDto) {
        return service.sendInterview(interviewDto);
    }

    @PostMapping("/add")
    public InterviewDto addStep(@RequestBody @Valid InterviewDto interviewDto) {
        return service.addStep(interviewDto);
    }
}
