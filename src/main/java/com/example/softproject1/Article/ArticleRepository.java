package com.example.softproject1.Article;

import com.example.softproject1.Application.Application;
import com.example.softproject1.User.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    // 게시글 ID 목록으로 게시글 조회
//    List<Article> findAllById(List<Long> ids);

    // 특정 게시글 ID로 게시글과 작성자 정보를 함께 조회
    @Query("SELECT a FROM Article a JOIN FETCH a.member WHERE a.id = :id")
    Optional<Article> findByIdWithMember(@Param("id") Long id);

    // 특정 memberEntity를 가진 사용자의 지원서로 게시글을 조회
//    @Query("SELECT DISTINCT a FROM Application ap JOIN ap.article a WHERE ap.member = :member")
//    List<Article> findArticlesByMember(@Param("member") MemberEntity member);

    List<Article> findByMemberId(Long userId);
}