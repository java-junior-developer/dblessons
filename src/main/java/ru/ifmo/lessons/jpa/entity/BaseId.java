package ru.ifmo.lessons.jpa.entity;

/* javax.validation -> hibernate-validator */
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.ToString;

@ToString
// родительский класс entity класса,
// который сам не является entity классом, дб отмечен
@MappedSuperclass
public abstract class BaseId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // SERIAL
    private int id;
}
