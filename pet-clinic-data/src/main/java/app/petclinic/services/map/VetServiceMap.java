package app.petclinic.services.map;

import app.petclinic.models.Specialty;
import app.petclinic.models.Vet;
import app.petclinic.services.SpecialtyService;
import app.petclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService
{
    private final SpecialtyService specialtyService;

    public VetServiceMap(SpecialtyService specialtyService)
    {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll()
    {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id)
    {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object)
    {
        if (object.getSpecialtys().size() > 0)
        {
            object.getSpecialtys().forEach(specialty -> {
                if (specialty.getId() == null)
                {
                    Specialty savedSpecialty = specialtyService.save(specialty);
                    specialty.setId(savedSpecialty.getId());
                }
            });
        }
        return super.save(object);
    }

    @Override
    public void delete(Vet object)
    {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id)
    {
        super.deleteById(id);

    }
}
