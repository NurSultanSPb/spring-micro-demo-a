package kz.nurs.micro.demo.authservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

//@Entity
//@Table(name = "tokens")
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class Token {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToOne
//    private User user;
//
//    private String token;
//
//    private Instant expiryDate;
//
//}