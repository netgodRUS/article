package ru.syn.article.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.syn.article.exceptions.ArticleNotFoundException;
import ru.syn.article.models.Article;
import ru.syn.article.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
public class ArticleServices {
    @Autowired private ArticleRepository repository;
    public List<Article> getAll (){
  return repository.findAll();
    }
    public void save (Article article){
        article.setId (new Random().nextLong());
        repository.save(article);
    }
    public void update (long id, Article article){
        article.setId(id);
        repository.save(article);
    }
    public void upVote (long id){
        Article article = (Article) repository.findAllById(id).orElseThrow(ArticleNotFoundException::new);
        article.setRaiting (article.getRating() + 1);
        repository.save(article);
    }

    public void downVote (long id){
        Article article = (Article) repository.findAllById(id).orElseThrow(ArticleNotFoundException::new);
        article.setRaiting (article.getRating() - 1);
        repository.save(article);
    }

    public List<Article> getBest(String category) {
        return repository.findAllByCategory(category)
                .stream()
                .filter(article -> article.getRating()>0)
                .sorted(Comparator.comparing(Article::getRating))
                .toList();


//        List <Article> allFromCategory = repository.findAllByCategory(category);
//        allFromCategory.sort(Comparator.comparing(Article::getRating));
//        List <Article> result = new ArrayList<>();
//        allFromCategory.forEach(article -> {
//            if (article.getRating() >0){
//                result.add(article);
//            }
//        });
//        return result;
    }

    public void create(Article article) {
    }
}
