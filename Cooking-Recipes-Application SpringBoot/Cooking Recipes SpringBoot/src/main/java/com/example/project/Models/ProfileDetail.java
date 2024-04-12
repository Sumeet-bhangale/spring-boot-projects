package com.example.project.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
//@Table(name = "place_suggestion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdDate;
    private Long userID;
    private String fullName;
    private String email;
    private String address;
    private String contact;
}
