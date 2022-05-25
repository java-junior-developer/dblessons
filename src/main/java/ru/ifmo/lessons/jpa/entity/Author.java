package ru.ifmo.lessons.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@NonNull // проверка на null в сеттерах
@Getter // генерация геттеров для всех полей
@Setter // генерация сеттеров для всех полей
@EqualsAndHashCode(callSuper = true, exclude = {"name", "age"})
@ToString(callSuper = true) // генерация метода toString
@Entity
// позволяет изменить имя таблицы в бд,
// добавить индексы и описать связи с другими таблицами
@Table(name = "tb_authors")
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
}
// @EqualsAndHashCode(callSuper=true)
// генерация метода toString (варианты):
// @ToString
// @ToString(callSuper=true, exclude="field1")
// @ToString(callSuper=true, exclude={"field1", "field2"})