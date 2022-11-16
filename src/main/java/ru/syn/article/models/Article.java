package ru.syn.article.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity (name = "articles")
public class Article {
    @Id
    private long id;
    private String category;
    private String title;
    private String text;
    private int rating;

    public Article() {
    }

    public void setId(long id) {

    }

    public Article(long id,String category, String title, String text, int rating) {
        this.id=id;
        this.category = category;
        this.title = title;
        this.text = text;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public int getRating() {
        return rating;
    }

    public void setRaiting(int id) {
    }
}
