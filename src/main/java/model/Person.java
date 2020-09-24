package model;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person {

    @Column(name = "name")
    private String name;

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
