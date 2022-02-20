package com.teck.kai.entity;

import javax.persistence.*;

@Entity
@Table(name = "combination")
public class CombinationBean {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String fields;

    public CombinationBean(String fields) {
        this.fields = fields;
    }

    public CombinationBean() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }
}
