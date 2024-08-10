package com.example.softproject1.Article;

import com.example.softproject1.Comment.Comment;
import lombok.Getter;

import java.util.List;

@Getter
public class ArticleWithCommentsDTO {
    private final Article article;
    private final List<Comment> comments;

    public ArticleWithCommentsDTO(Article article, List<Comment> comments) {
        this.article = article;
        this.comments = comments;
    }
}
