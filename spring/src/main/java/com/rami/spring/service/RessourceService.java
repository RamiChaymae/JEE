/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rami.spring.service;

import com.rami.spring.Repository.ProjetRepository;
import com.rami.spring.Repository.RessourceRepository;
import com.rami.spring.model.Projet;
import com.rami.spring.model.Ressource;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pc
 */

@Service


public class RessourceService {
    @Autowired 
    private RessourceRepository ressourceRepository ;
    
    public List<Ressource> findAllRes(){
        List<Ressource> res = ressourceRepository.findAll() ;
        return res ;
    }
    
    public void save(Ressource res){
        ressourceRepository.save(res) ;
    }
    
    public void deleteRes(Long id ){
        ressourceRepository.deleteById(id);
    }
    
    public Ressource findById(Long id) {
        return ressourceRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }
    
}
