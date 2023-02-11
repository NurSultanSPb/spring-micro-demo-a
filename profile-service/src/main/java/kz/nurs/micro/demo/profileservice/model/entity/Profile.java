package kz.nurs.micro.demo.profileservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "profile")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Profile {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    private String firstName;

    private String lastName;

    private LocalDate birthdayDate;

}
