package com.rami.spring.controller;


import com.rami.spring.Repository.ProjetRepository;
import com.rami.spring.model.Projet;
import com.rami.spring.service.ProjetService;
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
public class ProjetController {
    @Autowired
    private ProjetRepository projetRepository ;
    private final ProjetService projetService;
    
    @GetMapping("projet")
    public String listeProjet( Model model , @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name = "size",defaultValue = "4") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String kw) {
        
        Page<Projet> projet = projetRepository.findByTitreContains(kw, PageRequest.of(page,size));
        model.addAttribute("projets",projet.getContent());
        model.addAttribute("pages",new int[projet.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",kw);
        //List<Projet> projets = projetService.findAllProjet();
        //model.addAttribute( "projets" , projets);
        return "projet" ;
    }
    
    @GetMapping("/adminP/ajouterProjet")
    public String ajouterprojet(Model model){
        Projet projet = new Projet() ;
        model.addAttribute("projet" , projet) ;
        return "addProjet" ;
    }
    
    @PostMapping("/adminP/save")
    public String saveProject(@Valid @ModelAttribute("projet") Projet projet,
                               BindingResult result,
                               Model model){
       
        projetService.save(projet);
        return "redirect:/projet" ;
    }
    
    
    @GetMapping("/projet/descProjet/{description}")
    public String desc(@PathVariable(value = "description") String description , Model model){
  
        model.addAttribute("des" , description ) ;
        return "description" ;
    }
    
    @GetMapping("description")
    public String description(){
        return "description" ;
    }
    
    @GetMapping("/adminP/deleteProjet/{id}")
    public String deletProj(@PathVariable(value ="id") Long id ){
        projetService.deleteProjet(id) ;
        return "redirect:/projet" ;
    }
    
    @GetMapping("/adminP/editProjet/{id}")
    public String showEditUsersForm(@PathVariable Long id, Model model) {
        Projet projet = projetService.findById(id) ;
        model.addAttribute("projet", projet);
        return "editProjet";
    }

    @PostMapping("/adminP/update")
    public String updateUser(@Valid @ModelAttribute("projet") Projet projet , BindingResult result, Model model) {
        projetService.save(projet);
        return "redirect:/projet";
    }
    
}
