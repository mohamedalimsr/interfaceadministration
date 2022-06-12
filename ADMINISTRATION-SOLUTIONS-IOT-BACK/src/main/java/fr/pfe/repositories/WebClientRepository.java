package fr.pfe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.pfe.entities.WebClientAccount;

@Repository
public interface WebClientRepository extends JpaRepository<WebClientAccount, Long>{

}
