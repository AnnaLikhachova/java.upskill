package com.likhachova.orm.entity;

import com.likhachova.orm.annotation.Column;
import com.likhachova.orm.annotation.Id;
import com.likhachova.orm.annotation.Inject;
import com.likhachova.orm.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table
public class Person {
    @Id
    @Column
    private int id;
    @Column(name = "person_name")
    private String name;
    @Column(name = "person_salary")
    private double salary;

}
