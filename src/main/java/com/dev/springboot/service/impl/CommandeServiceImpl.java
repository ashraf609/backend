package com.dev.springboot.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dev.springboot.exception.CommandeNotFoundException;
import com.dev.springboot.model.Commande;
import com.dev.springboot.repo.CommandeRepository;
import com.dev.springboot.service.ICommandeService;

@Service
public class CommandeServiceImpl implements ICommandeService {

    @Autowired
    private CommandeRepository repo;

    @Override
    public Commande saveCommande(Commande commande) {
        return repo.save(commande);
    }

    @Override
    public List<Commande> getAllCommandes() {
        return repo.findAll();
    }

    @Override
    public Commande getCommandeById(Long num_commande) {
        Optional<Commande> opt = repo.findById(num_commande);
        if(opt.isPresent()) {
            return opt.get();
        } else {
            throw new CommandeNotFoundException("Commande with Id : "+num_commande+" Not Found");
        }
    }

    @Override
    public void deleteCommandeById(Long num_commande) {
        repo.delete(getCommandeById(num_commande));
    }

    @Override
    public void updateCommande(Commande commande) {
        repo.save(commande);
    }
}
