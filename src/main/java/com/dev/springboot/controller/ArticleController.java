package com.dev.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.dev.springboot.exception.ArticleNotFoundException;
import com.dev.springboot.model.Article;
import com.dev.springboot.service.IArticleService;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService service;

    @GetMapping("/")
    public String showDashboard() {

        return "redirect:https://cool-care-production.up.railway.app/dashboard";
    }
    @GetMapping("/home")
    public String showHomePage() {
        return "homePage";
    }

    @GetMapping("/register")
    public String showRegistration() {
        return "registerArticlePage";
    }

    @PostMapping("/save")
    public String saveArticle(
            @ModelAttribute Article article,
            @RequestParam("imageFile") MultipartFile imageFile, // Change to handle file upload
            Model model
    ) {
        if (!imageFile.isEmpty()) {
            // Process and save the image file, and set the image path to the article
            String imagePath = saveImage(imageFile); // Implement this method
            article.setImage(imagePath);
        }

        Long id = service.saveArticle(article).getNum_article();
        String message = "Record with num_article : '" + id + "' is saved successfully!";
        model.addAttribute("message", message);
        return "redirect:https://cool-care-production.up.railway.app/dashboard";
    }
    private String saveImage(MultipartFile file) {
        // Directory where the image will be saved
        String folder = "src/main/resources/static/images/";  // You need to replace this with the actual path
        try {
            // Create the directory if it doesn't exist
            Path directoryPath = Paths.get(folder);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            // Save the file
            Path filePath = directoryPath.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Return the file name (or you could return a relative path)
            return file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
            // You might want to handle this differently, depending on your application's needs
            return null;
        }
    }



    @GetMapping("/getAllArticles")
    public String getAllArticles(
            @RequestParam(value = "message", required = false) String message,
            Model model
    ) {
        List<Article> articles = service.getAllArticles();
        model.addAttribute("list", articles);
        model.addAttribute("message", message);
        return "allArticlePage";
    }

    @GetMapping("/edit")
    public String getEditPage(
            Model model,
            RedirectAttributes attributes,
            @RequestParam Long num_article
    ) {
        String page;
        try {
            Article article = service.getArticleByNumArticle(num_article);
            model.addAttribute("article", article);
            page = "editArticlePage";
        } catch (ArticleNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
            page = "redirect:https://cool-care-production.up.railway.app/dashboard";
        }
        return page;
    }

    @PostMapping("/update")
    public String updateArticle(
            @ModelAttribute Article article,
            @RequestParam("imageFile") MultipartFile imageFile, // Handle new image file
            RedirectAttributes attributes
    ) {
        if (!imageFile.isEmpty()) {
            String imagePath = saveImage(imageFile); // Implement this to save and return the image path
            article.setImage(imagePath);
        }
        // If imageFile is empty, the existing image info in article remains unchanged

        service.updateArticle(article);
        Long num_article = article.getNum_article();
        attributes.addAttribute("message", "Article with num_article: '" + num_article + "' is updated successfully!");
        return "redirect:https://cool-care-production.up.railway.app/dashboard";
    }


    @GetMapping("/delete")
    public String deleteArticle(
            @RequestParam Long num_article,
            RedirectAttributes attributes
    ) {
        try {
            service.deleteArticleByNumArticle(num_article);
            attributes.addAttribute("message", "Article with Num_article : '" + num_article + "' is removed successfully!");
        } catch (ArticleNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:https://cool-care-production.up.railway.app/dashboard";
    }
}
