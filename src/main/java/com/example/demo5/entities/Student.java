package com.example.demo5.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public int id;

    @Column(name = "first_name")
    @NotNull(message = "First name is required")
    @Size(min = 1,message = "First name is required")
    public String firstName;
    @Column(name = "last_name")
    @NotNull(message = "Last name is required")
//    @Size(min = 1,message = "Last name is required")
    public String lastName;

    @Column(name = "email")
    @NotNull(message = "Email is required")
    @Size(min = 1,message = "Email is required")
    @Email(message = "Email is not format")
    public String email;
    // When save action from a Form, it call setter
    // When render page, it call getter
    // Should create getter and setter for Entity
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Student(){

    }
    public Student( String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
