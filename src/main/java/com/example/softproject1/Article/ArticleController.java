package com.example.softproject1.Article;


import com.example.softproject1.Application.ResourceNotFoundException;
import com.example.softproject1.Comment.Comment;
import com.example.softproject1.Comment.CommentRepository;
import com.example.softproject1.Comment.CommentService;
import com.example.softproject1.User.MemberEntity;
import com.example.softproject1.User.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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


    @PostMapping("/articles/{user_id}") //게시글 쓰기
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


    @PutMapping("/articles/{user_id}/{id}") //게시글 수정하기
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

    @DeleteMapping("/articles/{user_id}/{id}") //게시글 삭제
    public ResponseEntity<Void> deleteArticle(@PathVariable("user_id") Long userId, @PathVariable Long id) {
        if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/articles") //게시글 목록
    public ResponseEntity<List<ArticleDTO>> index() {
        List<ArticleDTO> articles = articleService.getAllArticles(); // 전체 게시물 목록을 가져옵니다.
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }


    @GetMapping("/articles/{id}") //해당 게시글 보기
    public ResponseEntity<ArticleDTO> getArticle(@PathVariable Long id) {
        Article article = articleRepository.findByIdWithMember(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found"));

        ArticleDTO articleDto = new ArticleDTO(article);
        return ResponseEntity.ok(articleDto);
    }


    @GetMapping("/articles/{id}/with-comments") //해당게시글의 댓글
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

    @GetMapping("/articles/my/{user_id}") //내가쓴 글 보기 -> id만 전달
    public ResponseEntity<List<Long>> getArticlesByUserId(@PathVariable Long user_id) {
        List<Long> articleIds = articleService.getArticleIdsByUserId(user_id);
        return new ResponseEntity<>(articleIds, HttpStatus.OK);
    }

    @GetMapping("/articles/myarticle/{user_id}") // 내가 쓴 글 목록
    public ResponseEntity<List<ArticleDTO>> getArticlemyArticle(@PathVariable("user_id") Long userId) {
        List<ArticleDTO> articles = articleService.getArticlesByUserId(userId);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }


}
