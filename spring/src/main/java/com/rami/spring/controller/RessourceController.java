/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rami.spring.controller;

import com.rami.spring.Repository.RessourceRepository;
import com.rami.spring.model.Ressource;
import com.rami.spring.service.RessourceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author pc
 */


@Controller
@RequiredArgsConstructor

public class RessourceController {
    @Autowired
    private RessourceRepository ressourceRepository ;
    private final RessourceService ressourceService ;
    
    @GetMapping("ressource")
    public String listeRes( Model model , @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name = "size",defaultValue = "4") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String kw) {
        
        Page<Ressource> res = ressourceRepository.findByNomContains(kw, PageRequest.of(page,size));
        model.addAttribute("res",res.getContent());
        model.addAttribute("pages",new int[res.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",kw);
        //List<Ressource> ressources = ressourceService.findAllProjet();
        //model.addAttribute( "res" , ressources);
        return "ressource" ;
    }
    
    @GetMapping("adminRes/addRessource")
    public String ajouterres(Model model){
        Ressource res = new Ressource() ;
        model.addAttribute("res" , res) ;
        return "addRessource" ;
    }
    
    @PostMapping("/adminRes/save")
    public String saveres(@Valid @ModelAttribute("res") Ressource ressource,
                               BindingResult result,
                               Model model){
       
        ressourceService.save(ressource);
        return "redirect:/ressource" ;
    }
    
    
    
    @GetMapping("/adminRes/deleteRes/{id}")
    public String deletres(@PathVariable(value ="id") Long id ){
        ressourceService.deleteRes(id) ;
        return "redirect:/ressource" ;
    }
    
    @GetMapping("/adminRes/editRes/{id}")
    public String showEditres(@PathVariable Long id, Model model) {
        Ressource ressource = ressourceService.findById(id) ;
        model.addAttribute("res", ressource);
        return "editRessource";
    }

    @PostMapping("/adminRes/update")
    public String updateres(@Valid @ModelAttribute("res") Ressource ressource , BindingResult result, Model model) {
        ressourceService.save(ressource);
        return "redirect:/ressource";
    }
    
}
