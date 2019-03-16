package app.petclinic.services.map;

import app.petclinic.models.PetType;
import app.petclinic.services.PetTypeSevice;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PetTypeMapService extends AbstractMapService implements PetTypeSevice
{
    @Override
    public Set<PetType> findAll()
    {
        return this.findAll();
    }

    @Override
    public PetType findById(Long id)
    {
        return this.findById(id);
    }

    @Override
    public PetType save(PetType object)
    {
        return this.save(object);
    }

    @Override
    public void delete(PetType object)
    {
        this.delete(object);

    }

    @Override
    public void deleteById(Long id)
    {
        this.deleteById(id);

    }
}
