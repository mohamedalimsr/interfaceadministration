package fr.pfe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.pfe.entities.Boitier;

@Repository
public interface BoitierRepository extends JpaRepository<Boitier, Long>{

}
