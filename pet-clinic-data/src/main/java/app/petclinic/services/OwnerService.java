package app.petclinic.services;

import app.petclinic.models.Owner;

import java.util.Set;

public interface OwnerService
{
    Owner findByLastName(String lname);

    Owner findById(Long id);

    Owner save(Owner owner);

    Set<Owner> findAll();
}
