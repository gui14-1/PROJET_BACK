package org.example.API.controller;

import org.example.API.model.Article;
import org.example.API.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping
    public List<Article> getAllArticles(){
        return articleRepository.findAll();
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleRepository.save(article);
    }

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable String id) {
        return articleRepository.findById(id).orElse(null);
    }

    @GetMapping("/search/{nom}")
    public List<Article> searchArticleByName(@PathVariable String nom) {
        return articleRepository.findByName(nom);
    }

    @PutMapping("/{id}")
    public Article updateArticle(@PathVariable String id, @RequestBody Article article) {
        article.setId(id);
        return articleRepository.save(article);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable String id) {
        articleRepository.deleteById(id);
    }
}
