/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rami.spring.controller;


import com.rami.spring.model.Membre;
import com.rami.spring.service.MembreService;
import com.rami.spring.service.PublicationService;
import com.rami.spring.service.TableauService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author pc
 */

@Controller
@RequiredArgsConstructor

public class TableauController {
    
    
    private final TableauService tableauService ;
    private final PublicationService publicationService ;
    private final MembreService membreService ;
    
    @GetMapping("Home")
    public String home(Model model){
        
        String username = publicationService.UserName()  ;
        Membre membre = membreService.findByEmail(username) ;
        model.addAttribute("membre" , membre ) ;
        model.addAttribute("m" , tableauService.countM()) ;
        model.addAttribute("p" , tableauService.countP()) ;
        model.addAttribute("pub" , tableauService.countPub()) ;
        List<Object[]> roleCounts = tableauService.countUsersByRole();
        model.addAttribute("roleCounts", roleCounts);
      
        return "Home" ;
    }
}
