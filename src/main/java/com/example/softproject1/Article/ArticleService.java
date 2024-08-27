package com.example.softproject1.Article;

import com.example.softproject1.Application.Application;
import com.example.softproject1.Application.ResourceNotFoundException;
import com.example.softproject1.User.MemberEntity;
import com.example.softproject1.User.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final MemberRepository memberRepository;

    private final ArticleRepository applicationRepository;

    public void create(Article article) {
        articleRepository.save(article);
    }


    // 모든 게시물 가져오기
    public List<ArticleDTO> getAllArticles() {
        List<Article> articles = articleRepository.findAll(); // 모든 게시물 가져오기
        return articles.stream()
                .map(article -> new ArticleDTO(article)) // Article 엔티티를 DTO로 변환
                .collect(Collectors.toList());
    }

    private ArticleDTO convertToDTO(Article article) {
        // Article 엔티티를 ArticleDTO로 변환
        return new ArticleDTO(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getNumberOfPeople(),
                article.getDeadline(),
                article.getPreferentialTreatment(),
                article.getField(),
                article.getCreatedAt()
        );
    }

    //해당 멤버가 쓴 게시글 조회 -> 게시글의 id 전달
    public List<Long> getArticleIdsByUserId(Long memberId) {
        List<Article> articles = articleRepository.findByMemberId(memberId);
        return articles.stream()
                .map(Article::getId)
                .collect(Collectors.toList());
    }

    //해당 멤버가 쓴 게시글 목록 전달
    public List<ArticleDTO> getArticlesByUserId(Long userId) {
        List<Article> articles = articleRepository.findByMemberId(userId);
        return articles.stream()
                .map(ArticleDTO::new)
                .collect(Collectors.toList());
    }


}
