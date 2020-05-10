package com.bodify.bodify.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.HashMap;

@Getter
@Setter
public class User {

    @Id
    private String id;

    private String email;
    private String password;

    private String name;
    private Date dateOfBirth;
    private double weight;
    private int height;
    private HashMap<Date, Double> weightHistory;
}
