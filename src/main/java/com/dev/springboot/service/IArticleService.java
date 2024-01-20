package com.dev.springboot.service;

import java.util.List;
import com.dev.springboot.model.Article;

public interface IArticleService {

    Article saveArticle(Article article);
    List<Article> getAllArticles();
    Article getArticleByNumArticle(Long numArticle);
    void deleteArticleByNumArticle(Long numArticle);
    void updateArticle(Article article);

}
