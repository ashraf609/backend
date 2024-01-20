package com.dev.springboot.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dev.springboot.exception.ArticleNotFoundException;
import com.dev.springboot.model.Article;
import com.dev.springboot.repo.ArticleRepository;
import com.dev.springboot.service.IArticleService;

@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private ArticleRepository repo;

    @Override
    public Article saveArticle(Article article) {
        return repo.save(article);
    }

    @Override
    public List<Article> getAllArticles() {
        return repo.findAll();
    }

    @Override
    public Article getArticleByNumArticle(Long numArticle) {
        Optional<Article> opt = repo.findById(numArticle);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new ArticleNotFoundException("Article with Num_article: " + numArticle + " Not Found");
        }
    }

    @Override
    public void deleteArticleByNumArticle(Long numArticle) {
        repo.delete(getArticleByNumArticle(numArticle));
    }

    @Override
    public void updateArticle(Article article) {
        repo.save(article);
    }
}
