package com.dev.springboot.controller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

import com.dev.springboot.model.Client;
import com.dev.springboot.model.Commande;
import com.dev.springboot.service.IClientService;
import com.dev.springboot.service.ICommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.dev.springboot.exception.ArticleNotFoundException;
import com.dev.springboot.model.Article;
import com.dev.springboot.service.IArticleService;
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private IArticleService articleService;
    @Autowired
    private ICommandeService commandeService;
    @Autowired
    private IClientService clientService;

    @GetMapping
    public String showDashboard(@RequestParam(value = "message", required = false) String message, Model model) {
        List<Article> articles = articleService.getAllArticles();
        List<Commande> commandes = commandeService.getAllCommandes();
        List<Client> clients = clientService.getAllClients();

        model.addAttribute("articles", articles);
        model.addAttribute("commandes", commandes);
        model.addAttribute("clients", clients);
        model.addAttribute("message", message);

        return "Dashboard";
    }
    @Configuration
    public class CorsConfig implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("*") // Allow requests from any origin
                    .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow specific HTTP methods
                    .allowedHeaders("*"); // Allow all headers
        }
    }
}

