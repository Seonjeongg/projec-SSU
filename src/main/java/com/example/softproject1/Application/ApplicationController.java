package com.example.softproject1.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationRepository applicationRepository;

    @PostMapping("/apply")
    public void apply(@RequestParam Long articleId, @RequestParam Long memberId) {
        applicationService.apply(articleId, memberId);
    }

    @GetMapping("/{articleId}/application")
    public ResponseEntity<List<ApplicationDTO>> getApplicationsByArticle(@PathVariable Long articleId) {
        List<Application> applications = applicationRepository.findByArticleId(articleId);
        List<ApplicationDTO> applicationDtos = applications.stream()
                .map(ApplicationDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(applicationDtos);
    }

    // 개별 지원자의 프로필을 반환
    @GetMapping("/{applicationId}")
    public ResponseEntity<ApplicationDetailDTO> getApplicationDetail(@PathVariable Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));
        ApplicationDetailDTO applicationDetailDto = new ApplicationDetailDTO(application);
        return ResponseEntity.ok(applicationDetailDto);
    }
}
