package app.petclinic.repositories;

import app.petclinic.models.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long>
{
    Owner findByLname(String lname);

    List<Owner> findAllByLnameLike(String lname);
}
