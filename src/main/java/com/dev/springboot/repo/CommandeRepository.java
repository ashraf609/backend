package com.dev.springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dev.springboot.model.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

}
