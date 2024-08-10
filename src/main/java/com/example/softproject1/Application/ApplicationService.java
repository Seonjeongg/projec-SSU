package com.example.softproject1.Application;

import com.example.softproject1.Article.Article;
import com.example.softproject1.Article.ArticleRepository;
import com.example.softproject1.User.entity.MemberEntity;
import com.example.softproject1.User.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public void apply(Long articleId, Long memberId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article ID"));
        MemberEntity member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));

        Application application = new Application();
        application.setArticle(article);
        application.setMember(member);

        applicationRepository.save(application);
    }
}
