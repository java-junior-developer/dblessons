package ru.ifmo.lessons.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NonNull
@Entity
public class Nomination extends BaseId{
    private String title;
    private LocalDate date;

    @ManyToMany
    private List<Article> articles;

    public Nomination() {
        articles = new ArrayList<>();
    }
}
