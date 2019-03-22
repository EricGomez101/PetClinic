package app.petclinic.repositories;

import app.petclinic.models.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long>
{
    Owner findByLname(String lname);
}
