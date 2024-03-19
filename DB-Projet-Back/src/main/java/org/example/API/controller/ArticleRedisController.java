package org.example.API.controller;

import org.example.API.model.ArticleRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/redis/articles")
public class ArticleRedisController {

    @Autowired
    private RedisTemplate<String, ArticleRedis> redisTemplate;

    private static final String REDIS_KEY = "articles";
    private static final String NOM_INDEX_KEY = "name_index";

    @GetMapping("/{id}")
    public Object getArticle(@PathVariable String id){
        return redisTemplate.opsForHash().get(REDIS_KEY,id);
    }

    @PostMapping
    public void addArticle(@RequestBody ArticleRedis article){
        redisTemplate.opsForHash().put(REDIS_KEY,article.getId(),article);
        redisTemplate.expire(REDIS_KEY,1, TimeUnit.HOURS);
        redisTemplate.opsForHash().put(NOM_INDEX_KEY, article.getNom(), article.getId());
    }

    @GetMapping("/search/{name}")
    public String searchArticleByName(@PathVariable String name) {
        return (String) redisTemplate.opsForHash().get(NOM_INDEX_KEY, name);
    }
}
