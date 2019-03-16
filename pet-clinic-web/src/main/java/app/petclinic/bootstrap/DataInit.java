package app.petclinic.bootstrap;

import app.petclinic.models.Owner;
import app.petclinic.models.PetType;
import app.petclinic.models.Vet;
import app.petclinic.services.OwnerService;
import app.petclinic.services.PetTypeSevice;
import app.petclinic.services.VetService;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

@Component
public class DataInit implements CommandLineRunner
{
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeSevice petTypeSevice;

    public DataInit(OwnerService ownerService, VetService vetService, PetTypeSevice petTypeSevice)
    {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeSevice = petTypeSevice;
    }

    @Override
    public void run(String... args) throws Exception
    {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDog = petTypeSevice.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCat = petTypeSevice.save(cat);

        System.out.println("Loaded PetType's...");

        Owner owner1 = new Owner();
        owner1.setFname("Michael");
        owner1.setLname("Weston");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFname("Fiona");
        owner2.setLname("Glennann");

        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFname("Sam");
        vet1.setLname("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFname("Sam");
        vet2.setLname("ace");

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
