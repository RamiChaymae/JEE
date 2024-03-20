/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rami.spring.service;

import com.rami.spring.Repository.ProjetRepository;
import com.rami.spring.model.Projet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pc
 */

@Service

public class ProjetService {
    
    @Autowired 
    private ProjetRepository projetRepository ;
    
    public List<Projet> findAllProjet(){
        List<Projet> projets = projetRepository.findAll() ;
        return projets ;
    }
    
    public void save(Projet projet){
        System.out.println(projet.getDate_debut());
        projetRepository.save(projet) ;
    }
    
    public void deleteProjet(Long id ){
        projetRepository.deleteById(id);
    }
    
    public Projet findById(Long id) {
        return projetRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }
    
    
}
