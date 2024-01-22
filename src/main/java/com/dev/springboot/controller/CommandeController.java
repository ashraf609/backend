package com.dev.springboot.controller;

import java.util.List;

import com.dev.springboot.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.dev.springboot.exception.CommandeNotFoundException;
import com.dev.springboot.model.Commande;
import com.dev.springboot.service.ICommandeService;

@Controller
@RequestMapping("/commande")
public class CommandeController {

    @Autowired
    private ICommandeService service;

    @GetMapping("/")
    public String showDashboard() {

        return "redirect:https://gestion-commercial.up.railway.app/dashboard";
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "homePageCommande";
    }

    @GetMapping("/register")
    public String showRegistration() {
        return "registerCommandePage";
    }

    @PostMapping("/save")
    public String saveCommande(
            @ModelAttribute Commande commande,
            Model model
    ) {
        service.saveCommande(commande);
        Long id = service.saveCommande(commande).getNum_commande();
        String message = "Record with id : '"+id+"' is saved successfully !";
        model.addAttribute("message", message);
        return "redirect:https://gestion-commercial.up.railway.app/dashboard";
    }

    @GetMapping("/getAllCommandes")
    public String getAllCommandes(
            @RequestParam(value = "message", required = false) String message,
            Model model
    ) {
        List<Commande> commandes= service.getAllCommandes();
        model.addAttribute("list", commandes);
        model.addAttribute("message", message);
        return "allCommandePage";
    }

    @GetMapping("/edit")
    public String getEditPage(
            Model model,
            RedirectAttributes attributes,
            @RequestParam Long num_commande
    ) {
        String page = null;
        try {
            Commande commande = service.getCommandeById(num_commande);
            model.addAttribute("commande", commande);
            page="editCommandePage";
        } catch (CommandeNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
            page="redirect:https://gestion-commercial.up.railway.app/dashboard";
        }
        return page;
    }

    @PostMapping("/update")
    public String updateCommande(
            @ModelAttribute Commande commande,
            RedirectAttributes attributes
    ) {
        service.updateCommande(commande);
        Long num_commande = commande.getNum_commande();
        attributes.addAttribute("message", "Commande with id: '"+num_commande+"' is updated successfully !");
        return "redirect:https://gestion-commercial.up.railway.app/dashboard";
    }

    @GetMapping("/delete")
    public String deleteCommande(
            @RequestParam Long num_commande,
            RedirectAttributes attributes
    ) {
        try {
            service.deleteCommandeById(num_commande);
            attributes.addAttribute("message", "Commande with Id : '"+num_commande+"' is removed successfully!");
        } catch (CommandeNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:https://gestion-commercial.up.railway.app/dashboard";
    }
}
