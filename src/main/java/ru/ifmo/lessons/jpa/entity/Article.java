package ru.ifmo.lessons.jpa.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NonNull
@EqualsAndHashCode(callSuper = true, exclude = {"text", "createdOn"})
@Entity
public class Article extends BaseId{

    @Column(length = 100, unique = true, nullable = false)
    private String title;

    @Column(name = "article_text", columnDefinition = "TEXT NOT NULL")
    private String text;

    // если javax-persistence-api: Date Calendar
    private LocalDateTime createdOn;

    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;

    @ManyToMany(mappedBy = "articles")
   /* @JoinTable(name="имя_таблицы",
            joinColumns = @JoinColumn(name = "имя столбца для текущей таблицы"),
            inverseJoinColumns = @JoinColumn(name = "имя столбца для другой таблицы"))*/
    private List<Nomination> nominations;

    public Article() {
        createdOn = LocalDateTime.now();
        nominations = new ArrayList<>();
    }
}

// обязательно менять название таблицы, если классы называются
// User или Group, используя аннотацию @Table