/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rami.spring.controller;

  
import com.rami.spring.Repository.MembreRepository;
import com.rami.spring.model.Membre;
import com.rami.spring.service.MembreService;
import jakarta.validation.Valid;
import java.util.List;
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

public class MembreController {
    
    @Autowired
    private MembreRepository membreRepository ;
    private final MembreService membreService;
    
    
    @GetMapping({"/" , "/login" })
    public String loginForm(Model model) {
        Membre user = new Membre();
        model.addAttribute("membre", user);
        return "login";
    }
    
    @GetMapping("TEST" )
    public String l(Model model) {
        Membre user = new Membre();
        model.addAttribute("membre", user);
        return "TEST";
    }
    
    
     @GetMapping("adminM/AjouterMembre")
    public String ajouter(Model model) {
        Membre membre = new Membre();
        model.addAttribute("membre", membre);
        return "AjouterMembre";
    }
    
    @PostMapping("/save")
    public String savee(@RequestParam("rol") List<String> selectedRoles  ,
            @Valid @ModelAttribute("membre") Membre user,
                               BindingResult result,
                               Model model) {
        
        Membre existing = membreService.findByEmail(user.getEmail());
       
        if ( existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
            return "redirect:/adminM/AjouterMembre?error";
        }
        if (result.hasErrors()) {
            model.addAttribute("model", user);
            return "AjouterMembre";
        }
        membreService.saveUser(selectedRoles , user);
        return "redirect:/membre";
    }

    
    @PostMapping("/register/save")
    public String saveUsers(@RequestParam("rol") List<String> selectedRoles  ,
                               @Valid @ModelAttribute("membre") Membre user,
                               BindingResult result,
                               Model model) {
        Membre existing = membreService.findByEmail(user.getEmail());
       
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
            return "redirect:/login?error2";
        }
        
        if (result.hasErrors()) {
            model.addAttribute("model", user);
            return "login";
        }
        
        membreService.saveUser(selectedRoles , user);
        return "redirect:/login?success";
    
    }
    
    
    @GetMapping("membre")
    public String Membres(Model model ,
                        @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name = "size",defaultValue = "4") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String kw) {
        
        Page<Membre> membre = membreRepository.findByFirstNameContains(kw, PageRequest.of(page,size));
        model.addAttribute("membres",membre.getContent());
        model.addAttribute("pages",new int[membre.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",kw);
        //List<Membre> membres = membreService.findAllUsers();
        
        //model.addAttribute("membres", membres);
        return "membre";
    }

    @GetMapping("/adminM/editMembre/{id}")
    public String editMembre(@PathVariable Long id, Model model) {
        Membre membre = membreService.findById(id);
        model.addAttribute("membre", membre);
        return "editMembre";
    }

    @PostMapping("/adminM/update")
    public String updateMembre(@RequestParam("roles") List<String> selectedRoles  ,
            @RequestParam("pass") String pass  ,
            @Valid @ModelAttribute("membre") Membre membreDto , 
            BindingResult result, Model model) {
        
        membreService.updateUser(membreDto ,selectedRoles , pass );
        
        return "redirect:/membre";
    }

    @GetMapping("/adminM/delete/{id}")
    public String deleteMembre(@PathVariable(value = "id") Long id) {
        membreService.deleteUserById(id);
        return "redirect:/membre";
    }
}
