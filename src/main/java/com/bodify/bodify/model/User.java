package com.bodify.bodify.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;

@Getter
@Setter
@Data
@Document(collection = "users")
public class User {

    @Id
    @NotNull(message = "Id is mandatory")
    private String id;

    @NotNull(message = "Email is mandatory")
    @Email(message = "Invalid email")
    @Indexed(unique = true)
    private String email;

    @NotNull(message = "Password is mandatory")
    private String password;

    @NotNull(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Date is mandatory")
    private Date dateOfBirth = new Date();

    @NotNull(message = "Weight is mandatory")
    private double weight;

    @NotNull(message = "height is mandatory")
    private int height;
    private HashMap<String, Double> weightHistory;


}
