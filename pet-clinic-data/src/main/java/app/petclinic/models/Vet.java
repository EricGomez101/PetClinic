package app.petclinic.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vets")
public class Vet extends Person
{
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "vet_specialtys",
            joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name="specialty_id"))
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
