package app.petclinic.services;

import app.petclinic.models.Owner;


public interface OwnerService extends CrudService<Owner, Long>
{
    Owner findByLastName(String lname);
}
