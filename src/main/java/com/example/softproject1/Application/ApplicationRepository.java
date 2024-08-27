package com.example.softproject1.Application;

import com.example.softproject1.Article.Article;
import com.example.softproject1.User.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByArticleId(Long articleId);
    List<Application> findByMemberId(Long memberId);
    List<Application> findByMember(MemberEntity member);

    Collection<Object> findByArticleIdAndMemberId(Long articleId, Long memberId);
    boolean existsByArticleIdAndMemberId(Long articleId, Long memberId);
    List<Application> findByStatus(String status);

}