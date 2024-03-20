/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rami.spring.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author pc
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="projet")
public class Projet {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    
    @Column
    private String titre;

    
    @Column(length = 100000)
    private String description;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date  date_debut ; 

    
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_fin;
    
    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)
    private List<Publication> publications = new ArrayList<>();
    
    
}
