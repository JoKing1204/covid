package com.example.demo.game;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Game {
    @Id
    @SequenceGenerator(
            name = "game_sequence",
            sequenceName = "game_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "game_sequence"
    )

    private Long id;
    private String stationName;
    private String founder;
    private LocalDate doc;
    @Transient
    private Integer age;

    public Game() {
    }

//    public game(Long id, String name, String founder, LocalDate dob) {
//        this.id = id;
//        this.name = name;
//        this.founder = founder;
//        this.dob = dob;
//    }

    public Game(String stationName, String founder, LocalDate doc) {
        this.stationName = stationName;
        this.founder = founder;
        this.doc = doc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public LocalDate getDoc() {
        return doc;
    }

    public void setDoc(LocalDate doc) {
        this.doc = doc;
    }

    public Integer getAge() {
        return Period.between(this.doc, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", stationName='" + stationName + '\'' +
                ", founder='" + founder + '\'' +
                ", doc=" + doc +
                ", age=" + age +
                '}';
    }
}
