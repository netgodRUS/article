package ru.syn.article;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import ru.syn.article.models.Article;
import ru.syn.article.repository.ArticleRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArticleControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ArticleRepository repository;

    @Test
    public void getBestTest () throws URISyntaxException {
        repository.deleteAll();
        Article article1 = new Article(1,"sport", "a1", "aaaaaa1", 10);
        Article article2 = new Article(2,"sport", "a2", "aaaaaa2", 5);
        Article article3 = new Article(3,"sport", "a3", "aaaaaa3", 0);
        Article article4 = new Article(4, "sport", "a4", "aaaaaa4", -1);
        Article article5 = new Article(5,"politics", "p", "p", 100);

        repository.save(article1);
        repository.save(article2);
        repository.save(article3);
        repository.save(article4);
        repository.save(article5);

        ResponseEntity <List<Article>> responseEntity = restTemplate.exchange(
                new RequestEntity<>(HttpMethod.GET,new URI("/articles/best/category=sport")),
                new ParameterizedTypeReference<>() {
                });
        Assertions.assertEquals(200,responseEntity.getStatusCodeValue());
        List <Article> response = responseEntity.getBody();
        Assertions.assertEquals("a1",response.get(0).getTitle());
        Assertions.assertEquals("a2",response.get(0).getTitle());
        Assertions.assertEquals("a3",response.get(1).getTitle());
    }

}
