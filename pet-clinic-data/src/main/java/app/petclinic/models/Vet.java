package app.petclinic.models;

import java.util.HashSet;
import java.util.Set;

public class Vet extends Person
{
    private Set<Specialty> specialtys = new HashSet<>();

    public Set<Specialty> getSpecialtys()
    {
        return specialtys;
    }

    public void setSpecialtys(Set<Specialty> specialtys)
    {
        this.specialtys = specialtys;
    }
}
