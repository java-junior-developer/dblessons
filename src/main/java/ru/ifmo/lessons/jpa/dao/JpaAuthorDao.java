package ru.ifmo.lessons.jpa.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import ru.ifmo.lessons.dao.Dao;
import ru.ifmo.lessons.jpa.entity.Author;

import java.util.List;

public class JpaAuthorDao implements Dao<Author, Integer> {

    private EntityManager manager;

    public JpaAuthorDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Author add(Author author) {
        return null;
    }

    @Override
    public void update(Author author) {

    }

    @Override
    public void deleteByPK(Integer integer) {

    }

    @Override
    public void delete(Author author) {

    }

    @Override
    public Author getByPK(Integer integer) {
        return null;
    }



    @Override
    public List<Author> getAll() {
        /* 1. named native query getAllAuthors.native*/
        /* TypedQuery<Author> query =
                manager.createNamedQuery("getAllAuthors.native", Author.class);
        List<Author> authors = query.getResultList();*/

        /* 2. named JPQL query */
        /* TypedQuery<Author> query =
                manager.createNamedQuery("getAllAuthors.jpql", Author.class);
        List<Author> authors = query.getResultList(); */

        /* 3. JPQL query */
        /*String jpql = "SELECT a FROM Author a";
        TypedQuery<Author> query = manager.createQuery(jpql, Author.class);
        List<Author> authors = query.getResultList();*/

        // "SELECT author_name FROM tb_authors";
        String jpqlNames = "SELECT a.name FROM Author a";
        TypedQuery<String> queryNames =
                manager.createQuery(jpqlNames, String.class);
        List<String> names = queryNames.getResultList();
        System.out.println("NAMES: " + names);

        // SELECT * FROM tb_authors
        /* 4. Criteria API */
        // criteriaBuilder - формирует SQL запросы
        // criteriaQuery - SQL запрос (аналог SQL строки)
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery =
                criteriaBuilder.createQuery(Author.class); // тип данных результата
        Root<Author> root = criteriaQuery.from(Author.class); // откуда извлекаются данные
        criteriaQuery.select(root); // SELECT

        TypedQuery<Author> query = manager.createQuery(criteriaQuery);
        List<Author> authors = query.getResultList();
        return authors;
    }

    // SELECT a FROM Author a WHERE a.name = :name AND a.age = :age
    // SELECT a FROM Author a WHERE name = ? AND age = ?
    public Author getByNameAndAge(String name, int age) {
        /*TypedQuery<Author> query =
                manager.createNamedQuery("getByNameAndAge.jpql", Author.class);
        // query.setParameter(имя параметра из запроса (символы после :), значение)
        query.setParameter("name", name);
        query.setParameter("age", age);*/

        /*Author author = query.getSingleResult();*/
        // SELECT * FROM tb_author WHERE author_name = ? AND
                                      // age = ?
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery =
                criteriaBuilder.createQuery(Author.class); // тип данных результата

        // FROM tb_author
        Root<Author> root = criteriaQuery.from(Author.class); // откуда извлекаются данные

        // import jakarta.persistence.criteria.Predicate;
        // author_name = ?
        Predicate nameCond = criteriaBuilder.equal(root.get("name"), name);
        // age = ?
        Predicate ageCond = criteriaBuilder.equal(root.get("age"), age);

        Predicate allCond = criteriaBuilder.and(nameCond, ageCond);

        // SELECT *
        criteriaQuery.select(root).where(allCond);

        TypedQuery<Author> query = manager.createQuery(criteriaQuery);
        Author author = query.getSingleResult();
        return author;
    }
}
