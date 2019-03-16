package app.petclinic.models;

import java.util.Set;

public class Vet extends Person
{
    private Set<Specialty> specialtys;

    public Set<Specialty> getSpecialtys()
    {
        return specialtys;
    }

    public void setSpecialtys(Set<Specialty> specialtys)
    {
        this.specialtys = specialtys;
    }
}
