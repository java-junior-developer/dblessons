package ru.ifmo.lessons.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ru.ifmo.lessons.jpa.entity.Author;

public class Application {
    public static void main(String[] args) {
        // EntityManager создается фабриками,
        // под каждый persistence-unit своя фабрика
        // factory должна создаваться в () try или должна быть закрыта
        // вручную вызовом метода close
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("ormLesson");

        // manager должен создаваться в () try или быть закрыт
        // вручную вызовом метода close
        EntityManager manager = factory.createEntityManager();

        // import ru.ifmo.lessons.jpa.entity.Author;
        Author max = new Author();
        max.setName("Max");
        max.setAge(37);

        // добавление информации об объекте в таблицу
        manager.getTransaction().begin();
        // можно выполнить несколько запросов к бд, в случае ошибки,
        // можно вызвать метод manager.getTransaction().rollback();
        // тогда ни один запрос не будет выполнен, состояние бд
        // будет как на момент вызова метода manager.getTransaction().begin();

        manager.persist(max);

        manager.getTransaction().commit(); // подтверждение транзакции,
        // состояние бд меняется согласно выполненым запросам

        // получение по первичному ключу
        Author authorFromDb = manager.find(Author.class, 1);
        System.out.println("---authorFromDb---" + authorFromDb);

        // обновление:
        // изменение свойств объекта
        authorFromDb.setName("Maxim");

        // обновление информации в таблице
        manager.getTransaction().begin();
        manager.merge(max);
        manager.getTransaction().commit();

        authorFromDb = manager.find(Author.class, 1);
        System.out.println("---authorFromDb---" + authorFromDb);

        // удаление информации из таблицы
        manager.getTransaction().begin();
        manager.remove(max);
        manager.getTransaction().commit();






    }
}
