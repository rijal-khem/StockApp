package com.rizal.StockApp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rizal.StockApp.model.Company;
import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;

    @JsonProperty("firstName")
    @Column(name="firstName")
    private String userFirstName;

    @JsonProperty("email")
    @Column(name ="email")
    private String email;

    @Column(name="companies")
    @ElementCollection(targetClass=Long.class)
    private List<Long> companies = new ArrayList<>();

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Long> getCompanies() {
        return companies;
    }

    public void setCompanies(HashSet<Company> companiesList) {
        this.companies = companies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserFirstName().equals(user.getUserFirstName()) && getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserFirstName(), getEmail());
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userFirstName='" + userFirstName + '\'' +
                ", email='" + email + '\'' +
                ", companies=" + companies +
                '}';
    }
}
