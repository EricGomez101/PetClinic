package app.petclinic.services.springdatajpa;

import app.petclinic.models.Owner;
import app.petclinic.repositories.OwnerRepository;
import app.petclinic.repositories.PetRepository;
import app.petclinic.repositories.PetTypeRepository;
import app.petclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("jpa")
public class OwnerJPAService implements OwnerService
{
    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerJPAService(OwnerRepository ownerRepository,
                           PetRepository petRepository,
                           PetTypeRepository petTypeRepository)
    {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Owner findByLastName(String lname)
    {
        return ownerRepository.findByLname(lname);
    }

    @Override
    public Set<Owner> findAll()
    {
        Set<Owner> owners = new HashSet<>();

        ownerRepository.findAll().forEach(owners::add);

        return owners;
    }

    @Override
    public Owner findById(Long aLong)
    {
        return ownerRepository.findById(aLong).orElse(null);
    }

    @Override
    public Owner save(Owner object)
    {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object)
    {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong)
    {
        ownerRepository.deleteById(aLong);

    }

    @Override
    public List<Owner> findAllByLastNameLike(String lname)
    {
        return ownerRepository.findAllByLnameLike(lname);
    }
}
