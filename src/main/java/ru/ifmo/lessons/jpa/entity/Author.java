package ru.ifmo.lessons.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NonNull // проверка на null в сеттерах
@Getter // генерация геттеров для всех полей
@Setter // генерация сеттеров для всех полей
@EqualsAndHashCode(callSuper = true, exclude = {"name", "age"})
@ToString(callSuper = true, exclude = "articles") // генерация метода toString
@Entity
// позволяет изменить имя таблицы в бд,
// добавить индексы и описать связи с другими таблицами
@Table(name = "tb_authors")
@NamedNativeQueries({ // SQL - в запросе используются названия из БД
        @NamedNativeQuery(name = "getAllAuthors.native",
                query = "SELECT id, author_name, age FROM tb_authors",
                resultClass = Author.class)
        /*, следующий @NamedNativeQuery */
})
@NamedQueries({ // JPQL - в запросе используются названия из программы
    @NamedQuery(name = "getAllAuthors.jpql",
                query = "SELECT a FROM Author a"),
    @NamedQuery(name = "getByNameAndAge.jpql",
       query = "SELECT a FROM Author a WHERE a.name = :name AND a.age = :age")
})
public class Author extends BaseId{
    // @Getter и @Setter можно указывать для определенных
    // свойств (полей), тогда методы сгенерируются только
    // для этих свойств
    /*@Id // поле станет первичным ключем в таблице
    @GeneratedValue(strategy = GenerationType.AUTO) // автоинкремент аналогично SEQUENCE
    private int id;*/
    // columnDefinition="описание столбца на SQL синтаксисе"
    @Column(name = "author_name", length = 100, nullable = false) // описание столбца
    private String name;
    private int age;

    // fetch = FetchType.LAZY (извлечение по запросу - .getArticles())
    // fetch = FetchType.EAGER (безусловное извлечение)
    // orphanRemoval
    // cascade -
    // ALL - все дейсвия
    // PERSIST - добавление
    // MERGE, REFRESH - обновление
    // REMOVE, DETACH - удаление
    @OneToMany(mappedBy = "author", cascade = CascadeType.PERSIST)
    private List<Article> articles;

    public Author() {
        articles = new ArrayList<>();
    }
}
// @EqualsAndHashCode(callSuper=true)
// генерация метода toString (варианты):
// @ToString
// @ToString(callSuper=true, exclude="field1")
// @ToString(callSuper=true, exclude={"field1", "field2"})

// @OneToMany : @ManyToOne - 1 : N
// @ManyToMany : @ManyToMany - N : N
// @OneToOne : @OneToOne - 1 : 1
