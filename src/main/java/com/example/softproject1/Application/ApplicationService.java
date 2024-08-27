package com.example.softproject1.Application;

import com.example.softproject1.Article.Article;
import com.example.softproject1.Article.ArticleDTO;
import com.example.softproject1.Article.ArticleRepository;
import com.example.softproject1.User.MemberEntity;
import com.example.softproject1.User.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public Long apply(Long articleId, Long memberId) {
        // 게시글을 조회
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article ID"));
        // 지원자를 조회
        MemberEntity member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));

        // 작성자가 자신의 게시글에 지원하는 것을 방지
        if (article.getMember().getId().equals(memberId)) {
            throw new IllegalStateException("Author cannot apply to their own article");
        }

        // 이미 지원한 사용자인지 확인
        boolean alreadyApplied = !applicationRepository.findByArticleIdAndMemberId(articleId, memberId).isEmpty();
        if (alreadyApplied) {
            throw new IllegalStateException("User has already applied for this article");
        }

        // 새로운 지원서 생성 및 저장
        Application application = new Application();
        application.setArticle(article);
        application.setMember(member);

        Application savedApplication = applicationRepository.save(application);
        return savedApplication.getId(); // 저장된 지원서의 ID 반환
    }
    // 사용자 ID로 지원한 게시글 목록을 반환하는 메서드
    public List<ArticleDTO> getAppliedArticles(Long memberId) {
        List<Application> applications = applicationRepository.findByMemberId(memberId);
        return applications.stream()
                .map(application -> new ArticleDTO(application.getArticle())) // ArticleDTO 생성자에 Article 객체를 전달
                .collect(Collectors.toList());
    }

    public void cancelApplication(Long applicationId) {
        // 지원서 ID로 지원서 찾기
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        // 지원서 삭제
        applicationRepository.delete(application);
    }

    public boolean isApplied(Long articleId, Long memberId) {
        // 주어진 게시글 ID와 회원 ID로 지원서가 존재하는지 확인
        return applicationRepository.existsByArticleIdAndMemberId(articleId, memberId);
    }

    public void updateStatus(Long applicationId, String status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid application ID"));
        application.setStatus(status);
        applicationRepository.save(application);
    }

    public List<Long> getConfirmedMemberIds() {
        return applicationRepository.findByStatus("승인").stream()
                .map(application -> application.getMember().getId())
                .collect(Collectors.toList());
    }
}
