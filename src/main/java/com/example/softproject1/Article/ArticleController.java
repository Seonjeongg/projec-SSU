package com.example.softproject1.Article;


import com.example.softproject1.Application.ResourceNotFoundException;
import com.example.softproject1.Comment.Comment;
import com.example.softproject1.Comment.CommentRepository;
import com.example.softproject1.Comment.CommentService;
import com.example.softproject1.User.entity.MemberEntity;
import com.example.softproject1.User.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;


    @PostMapping("/articles/{user_id}")
    public ResponseEntity<String> createArticle(@PathVariable("user_id") Long userId, @RequestBody ArticleDTO articleDTO) {
        try {
        // Member를 데이터베이스에서 찾음
            Optional<MemberEntity> optionalMember = memberRepository.findById(userId);
            if (!optionalMember.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Member not found");
        }

            MemberEntity member = optionalMember.get();

        // DTO를 Article 엔티티로 변환
            Article article = new Article();
            article.setTitle(articleDTO.getTitle());
            article.setContent(articleDTO.getContent());
            article.setNumberOfPeople(articleDTO.getNumberOfPeople());
            article.setDeadline(articleDTO.getDeadline());
            article.setPreferentialTreatment(articleDTO.getPreferentialTreatment());
            article.setField(articleDTO.getField());
            article.setMember(member);  // Member와 연결

        // Article 서비스 호출
        articleRepository.save(article);

        return ResponseEntity.status(HttpStatus.CREATED).body("Article created successfully");
    } catch (Exception e) {
        // 예외 처리
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
    }
}


    @PutMapping("/articles/{user_id}/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable("user_id") Long userId, @PathVariable Long id, @RequestBody ArticleDTO updatedArticleDTO) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            article.setTitle(updatedArticleDTO.getTitle());
            article.setContent(updatedArticleDTO.getContent());
            article.setNumberOfPeople(updatedArticleDTO.getNumberOfPeople());
            article.setDeadline(updatedArticleDTO.getDeadline());
            article.setPreferentialTreatment(updatedArticleDTO.getPreferentialTreatment());
            article.setField(updatedArticleDTO.getField());

            // Member 연결 (선택 사항)
            if (updatedArticleDTO.getMemberId() != null) {
                Optional<MemberEntity> optionalMember = memberRepository.findById(userId);
                optionalMember.ifPresent(article::setMember);
            }

            Article savedArticle = articleRepository.save(article);
            return ResponseEntity.ok(savedArticle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/articles/{user_id}/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("user_id") Long userId, @PathVariable Long id) {
        if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<ArticleDTO>> index(@RequestParam(value = "page", defaultValue = "0") int page) {
        Page<ArticleDTO> paging = articleService.getList(page);
        return new ResponseEntity<>(paging, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticle(@PathVariable Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Article not found"));
        ArticleDTO articleDto = new ArticleDTO(article);
        return ResponseEntity.ok(articleDto);
}


    @GetMapping("/articles/{id}/with-comments")
    public ResponseEntity<ArticleWithCommentsDTO> getArticleWithComments(@PathVariable Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            List<Comment> comments = commentService.getCommentsByArticleId(id);
            ArticleWithCommentsDTO articleWithCommentsDTO = new ArticleWithCommentsDTO(article, comments);
            return ResponseEntity.ok(articleWithCommentsDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
