package com.managementkasmasjid.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CommonUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_common_user")
    private GlobalParam categoryCommonUser;

    public CommonUser() {
    }

    public CommonUser(String name, String phoneNumber, String email, String address, GlobalParam categoryCommonUser) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.categoryCommonUser = categoryCommonUser;
    }

    @Override
    public String toString() {
        return "CommonUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", categoryCommonUser=" + categoryCommonUser +
                '}';
    }
}
