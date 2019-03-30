package app.petclinic.services;

import app.petclinic.models.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long>
{
    Owner findByLastName(String lname);

    List<Owner> findAllByLastNameLike(String lname);
}
