package com.example.softproject1.Application;

import com.example.softproject1.Article.ArticleDTO;
import com.example.softproject1.Article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/applications")
@CrossOrigin("*")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ArticleService articleService;

    @PostMapping("/apply") //지원하기
    public ResponseEntity<Long> apply(@RequestParam Long articleId, @RequestParam Long memberId) {
        Long applicationId = applicationService.apply(articleId, memberId);
        return ResponseEntity.ok(applicationId); // 지원서 ID를 응답으로 반환
    }



    @GetMapping("/apply/is/{articleId}/user/{userId}") //해당 게시글에 지원 했는지 안했는지
    public ResponseEntity<Boolean> isApply(@PathVariable Long articleId, @PathVariable Long userId) {
        boolean applied = applicationService.isApplied(articleId, userId);
        return ResponseEntity.ok(applied);
    }


    @DeleteMapping("/apply/{applicationId}") //지원 삭제
    public ResponseEntity<Void> cancelApplication(@PathVariable Long applicationId) {
        applicationService.cancelApplication(applicationId);
        return ResponseEntity.noContent().build(); // No Content 상태 반환
    }

    @GetMapping("/apply/{articleId}") //해당 게시글의 지원자 목록
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

    @GetMapping("/apply/my/{memberId}") //내가 지원한 게시글 목록
    public ResponseEntity<List<ArticleDTO>> getAppliedArticles(@PathVariable Long memberId) {
        List<ArticleDTO> articles = applicationService.getAppliedArticles(memberId);
        return ResponseEntity.ok(articles);
    }


    @PostMapping("/{applicationId}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long applicationId, @RequestParam String status) {
        applicationService.updateStatus(applicationId, status);
        return ResponseEntity.ok().build();
    }


}
