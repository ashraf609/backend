package com.dev.springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dev.springboot.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
