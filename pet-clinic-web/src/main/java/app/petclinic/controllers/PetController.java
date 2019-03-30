package app.petclinic.controllers;

import app.petclinic.models.Owner;
import app.petclinic.models.PetType;
import app.petclinic.services.OwnerService;
import app.petclinic.services.PetService;
import app.petclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{id}")
public class PetController
{
    private final static String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

    private final OwnerService ownerService;
    private final PetService petService;
    private final PetTypeService petTypeService;

    public PetController(OwnerService ownerService, PetService petService, PetTypeService petTypeService)
    {
        this.ownerService = ownerService;
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes()
    {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long id)
    {
        return ownerService.findById(id);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder)
    {
        dataBinder.setDisallowedFields("id");
    }


}
