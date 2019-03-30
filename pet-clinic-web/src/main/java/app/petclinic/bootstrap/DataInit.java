package app.petclinic.bootstrap;

import app.petclinic.models.*;
import app.petclinic.services.*;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;

@Component
public class DataInit implements CommandLineRunner
{
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeSevice petTypeSevice;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataInit(OwnerService ownerService, VetService vetService, PetTypeSevice petTypeSevice, SpecialtyService specialtyService, VisitService visitService)
    {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeSevice = petTypeSevice;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception
    {
        int count = petTypeSevice.findAll().size();

        if (count == 0)
        {
            loadData();
        }
    }

    private void loadData()
    {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDog = petTypeSevice.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCat = petTypeSevice.save(cat);

        System.out.println("Loaded PetType's...");

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        radiology.setDescription("surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        radiology.setDescription("dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        System.out.println("Loaded Specialty's");

        Owner owner1 = new Owner();
        owner1.setFname("Michael");
        owner1.setLname("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("1223244322");
        // created a pet for owner1.
        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDog);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFname("Fiona");
        owner2.setLname("Glennann");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("1223244322");
        // creating a pet for owner2
        Pet fionasPet = new Pet();
        fionasPet.setPetType(savedCat);
        fionasPet.setOwner(owner2);
        fionasPet.setBirthDate(LocalDate.now());
        fionasPet.setName("Just Cat");
        owner2.getPets().add(fionasPet);

        ownerService.save(owner2);

        // adding a visit for fionas cat.
        Visit catVisit = new Visit();
        catVisit.setPet(fionasPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");

        visitService.save(catVisit);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFname("Sam");
        vet1.setLname("Axe");
        vet1.getSpecialtys().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFname("Sam");
        vet2.setLname("ace");
        vet2.getSpecialtys().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets...");

    }
}
