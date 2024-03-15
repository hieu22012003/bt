package com.example.demo5.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "classroom")
public class ClassRoom {

    @Id
    @Column(name="id")
    private String id;
    @NotNull(message = " name is required")
    @Column(name = "name")
    private String name;
    @NotNull(message = "number member is required")
    @Column(name = "number_member")
    private String number_member;
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber_member() {
        return number_member;
    }

    public void setNumber_member(String number_member) {
        this.number_member = number_member;
    }

    public String getId() {
        return id;
    }
    public ClassRoom(){

    }
    public ClassRoom(String id, String name, String number_member) {
        this.id = id;
        this.name = name;
        this.number_member = number_member;
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number_member='" + number_member + '\'' +
                '}';
    }
}
