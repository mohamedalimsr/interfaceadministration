package fr.pfe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.pfe.entities.AdministratorAccount;

@Repository
public interface AdministratorRepository extends JpaRepository<AdministratorAccount, Long>{

}
