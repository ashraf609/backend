package com.dev.springboot.service;

import java.util.List;
import com.dev.springboot.model.Commande;

public interface ICommandeService {

    public Commande saveCommande(Commande commande);
    public List<Commande> getAllCommandes();
    public Commande getCommandeById(Long num_commande);
    public void deleteCommandeById(Long num_commande);
    public void updateCommande(Commande commande);

}
