package by.wevioz.controller;

import by.wevioz.dto.InterviewDto;
import by.wevioz.service.InterviewService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
