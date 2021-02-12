package ma.enset.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ma.enset.client.entities.Client;



@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {

}
