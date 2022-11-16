package ru.syn.article.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.syn.article.models.Article;
import ru.syn.article.repository.ArticleRepository;
import ru.syn.article.services.ArticleServices;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping ("/articles")
public class ArticlesController {
    @Autowired
    private ArticleServices services;



    @GetMapping ("/all")
    public List<Article> getArticle (){
        return services.getAll();
//        List <Article> result = new ArrayList<>();
//        Article article = new Article(1, "Спорт", "все хорошо", "все идет как надо", 100);
//        result.add(article);
//return result;
    }
    @PostMapping
    public void createArticle(@RequestBody Article article){
        services.create(article);

    }
    @PostMapping ("/{articleId}")
    public void updateArticle (@RequestBody Article article, @PathVariable long articleId){
        services.update(articleId,article);

    }
    @PostMapping ("/{atricleId}/raiting/up")
    public void upVote (@PathVariable long articleId){
        services.upVote(articleId);
    }
    @PostMapping ("/{atricleId}/raiting/down")
    public void downVote (@PathVariable long articleId){
        services.downVote(articleId);;
    }

    @GetMapping ("/best")
    public List <Article> getBest (@RequestParam String category){
        return services.getBest(category);
    }

}
