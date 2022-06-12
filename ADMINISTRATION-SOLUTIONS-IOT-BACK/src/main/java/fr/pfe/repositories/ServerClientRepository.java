package fr.pfe.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import fr.pfe.entities.ServerClientAccount;

@Repository
public interface ServerClientRepository extends PagingAndSortingRepository<ServerClientAccount, Long>{

}
