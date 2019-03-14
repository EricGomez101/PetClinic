package app.petclinic.bootstrap;

import app.petclinic.models.Owner;
import app.petclinic.models.Vet;
import app.petclinic.services.OwnerService;
import app.petclinic.services.VetService;
import app.petclinic.services.map.OwnerServiceMap;
import app.petclinic.services.map.VetServiceMap;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

@Component
public class DataInit implements CommandLineRunner
{
    private final OwnerService ownerService;
    private final VetService vetService;

    public DataInit(OwnerService ownerService, VetService vetService)
    {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception
    {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFname("Michael");
        owner1.setLname("Weston");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFname("Fiona");
        owner2.setLname("Glennann");

        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFname("Sam");
        vet1.setLname("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFname("Sam");
        vet2.setLname("ace");

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
