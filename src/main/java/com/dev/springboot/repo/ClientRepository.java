package com.dev.springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dev.springboot.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
