package com.example.softproject1.Comment;

import com.example.softproject1.Article.Article;
import com.example.softproject1.Article.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public Comment addComment(Long articleId, CommentDTO commentDTO) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setAuthor(commentDTO.getAuthor());
        comment.setArticle(article);

        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByArticleId(Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
