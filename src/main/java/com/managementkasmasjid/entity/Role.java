package com.managementkasmasjid.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role_code", unique = true, nullable = false, length = 64)
    private String code;

    @Column(name = "role_name", unique = true, nullable = false, length = 100)
    private String name;

//    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles", cascade = CascadeType.ALL)
//    private Set<User> users = new HashSet<>();

    public Role() {
    }

    public Role(String code, String name) {
        this.code = code;
        this.name = name;
//        this.users = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Role.class.getSimpleName() + "[", "]")
                .add("id='" + this.id + "'")
                .add("code='" + this.code + "'")
                .add("name='" + this.name + "'")
                .toString();
    }
}
