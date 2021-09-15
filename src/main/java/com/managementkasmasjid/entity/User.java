package com.managementkasmasjid.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    //    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
//    @JoinTable(name = "user_role_tab", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//    private Set<Role> roles = new HashSet<>();
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "role")
    private Role role;

    public User() {
    }

    public User(String username, String email, String password,Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public boolean isRoleSelected(Long roleId){
        if (roleId != null) {
            return roleId.equals(this.role.getId());
        }
        return false;
    }

//    public void addRole(Role item) {
//        this.roles.add(item);
//        item.getUsers().add(this);
//    }

//    public void removeRole(Role item) {
//        this.roles.remove(item);
//        item.getUsers().remove(this);
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    //    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
