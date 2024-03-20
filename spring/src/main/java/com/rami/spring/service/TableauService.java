/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rami.spring.service;

import com.rami.spring.Repository.MembreRepository;
import com.rami.spring.Repository.ProjetRepository;
import com.rami.spring.Repository.PublicationRepository;
import com.rami.spring.Repository.RoleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pc
 */

@Service

public class TableauService {
    
    @Autowired
    private MembreRepository membreRepository ;
    
    @Autowired
    private PublicationRepository publicationRepository ;
    
    @Autowired
    private ProjetRepository projetRepository ;
    
    @Autowired
    private RoleRepository roleRepository;
    
    public Long countM(){
        return membreRepository.count() ;
    }
    
    public Long countP(){
        return projetRepository.count() ;
    }
    public Long countPub(){
        return publicationRepository.count() ;
    }
    public List<Object[]> countUsersByRole() {
        return roleRepository.countUsersByRole();
    }
}
