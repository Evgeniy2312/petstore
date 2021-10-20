package com.example.swagger.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private  long id;
    @NotEmpty
    @NotBlank
    @NotNull
    private String username;
    @NotEmpty
    @NotBlank
    @NotNull
    private String firstName;
    @NotEmpty
    @NotBlank
    @NotNull
    private String lastName;
    @NotEmpty
    @NotBlank
    @NotNull
    private String email;
    @NotEmpty
    @NotBlank
    @NotNull
    private String password;
    @NotEmpty
    @NotBlank
    @NotNull
    private String phone;
    private int userStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", userStatus=" + userStatus +
                '}';
    }
}
