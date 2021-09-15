package com.managementkasmasjid.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class GlobalParam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String paramCondition;
    private String paramDesc;
    private String paramValue;
    private String info;

    public GlobalParam() {
    }

    public GlobalParam(String paramCondition, String paramDesc, String paramValue, String info) {
        this.paramCondition = paramCondition;
        this.paramDesc = paramDesc;
        this.paramValue = paramValue;
        this.info = info;
    }

    @Override
    public String toString() {
        return "GlobalParam{" +
                "id=" + id +
                ", paramCondition='" + paramCondition + '\'' +
                ", paramDesc='" + paramDesc + '\'' +
                ", paramValue='" + paramValue + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
