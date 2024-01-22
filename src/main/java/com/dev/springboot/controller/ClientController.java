package com.dev.springboot.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
import com.dev.springboot.exception.ClientNotFoundException;
import com.dev.springboot.model.Client;
import com.dev.springboot.service.IClientService;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private IClientService service;

    @GetMapping("/")
    public String showDashboard() {

        return "redirect:https://gestion-commercial.up.railway.app/dashboard";
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "homePage"; // Make sure this view exists
    }

    @GetMapping("/register")
    public String showRegistration() {
        return "registerClientPage"; // Make sure this view exists
    }

    @PostMapping("/save")
    public String saveClient(@ModelAttribute Client client, @RequestParam("imageFile") MultipartFile imageFile, Model model) {
        if (!imageFile.isEmpty()) {
            String imagePath = saveImage(imageFile);
            client.setImage(imagePath);
        }
        Long id = service.saveClient(client).getNum_client();
        String message = "Record with num_client : '" + id + "' is saved successfully!";
        model.addAttribute("message", message);
        return "redirect:https://gestion-commercial.up.railway.app/dashboard";
    }

    private String saveImage(MultipartFile file) {
        // Directory where the image will be saved
        String folder = "src/main/resources/static/images/";
        try {
            Path directoryPath = Paths.get(folder);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }
            Path filePath = directoryPath.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/getAllClients")
    public String getAllClients(@RequestParam(value = "message", required = false) String message, Model model) {
        List<Client> clients = service.getAllClients();
        model.addAttribute("list", clients);
        model.addAttribute("message", message);
        return "allClientPage"; // Make sure this view exists
    }

    @GetMapping("/edit")
    public String getEditPage(Model model, RedirectAttributes attributes, @RequestParam Long num_client) {
        try {
            Client client = service.getClientById(num_client);
            model.addAttribute("client", client);
            return "editClientPage"; // Make sure this view exists
        } catch (ClientNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
            return "redirect:https://gestion-commercial.up.railway.app/dashboard";
        }
    }

    @PostMapping("/update")
    public String updateClient(@ModelAttribute Client client, @RequestParam("imageFile") MultipartFile imageFile, RedirectAttributes attributes) {
        if (!imageFile.isEmpty()) {
            String imagePath = saveImage(imageFile);
            client.setImage(imagePath);
        }
        service.updateClient(client);
        Long num_client = client.getNum_client();
        attributes.addAttribute("message", "Client with num_client: '" + num_client + "' is updated successfully!");
        return "redirect:https://gestion-commercial.up.railway.app/dashboard";
    }

    @GetMapping("/delete")
    public String deleteClient(@RequestParam Long num_client, RedirectAttributes attributes) {
        try {
            service.deleteClientById(num_client);
            attributes.addAttribute("message", "Client with Num_client : '" + num_client + "' is removed successfully!");
        } catch (ClientNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:https://gestion-commercial.up.railway.app/dashboard";
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
