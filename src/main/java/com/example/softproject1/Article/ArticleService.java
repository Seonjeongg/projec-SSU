package com.example.softproject1.Article;

import com.example.softproject1.User.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final MemberRepository memberRepository;

    public void create(Article article) {
        articleRepository.save(article);
    }


    public Page<ArticleDTO> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Article> articles = articleRepository.findAll(pageable);
        return articles.map(this::convertToDTO); // Article 엔티티를 ArticleDTO로 변환
    }

    private ArticleDTO convertToDTO(Article article) {
        // Article 엔티티를 ArticleDTO로 변환
        return new ArticleDTO(
//                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getNumberOfPeople(),
                article.getDeadline(),
                article.getPreferentialTreatment(),
                article.getField()
        );
    }




}
