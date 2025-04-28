package com.example.data_validation.entties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "user_name")
    @NotEmpty(message = "Username cannot be Empty")
    private String userName;

    @Column(name = "age")
    @Min(value = 13, message = "User must be atleast 13 or above to sign in to the platform")
    private Integer age;

    public User(String email, String userName, Integer age) {
        this.email = email;
        this.userName = userName;
        this.age = age;
    }
}
