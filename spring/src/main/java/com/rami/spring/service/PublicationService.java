/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rami.spring.service;


import com.rami.spring.Repository.PublicationRepository;
import com.rami.spring.model.Publication;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author pc
 */

@Service

public class PublicationService {
    @Autowired 
    private PublicationRepository publicationRepository ;
    
    public List<Publication> findAllPub(){
        List<Publication> pubs = publicationRepository.findAll() ;
        return pubs ;
    }
    
    public void save(Publication pub){
        
        publicationRepository.save(pub) ;
    }
    
    public void deletePub(Long id ){
        publicationRepository.deleteById(id);
    }
    
    public Publication findById(Long id) {
        return publicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }
    
    public Date date(Long id){
        Publication pub = publicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found")) ;
        return pub.getDate_pub() ;
    }
    public String UserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        
        if (authentication != null && authentication.isAuthenticated()) {
            
            String nomUtilisateur = authentication.getName();
            return nomUtilisateur;
        }

        
        return null;
    }

  

}
