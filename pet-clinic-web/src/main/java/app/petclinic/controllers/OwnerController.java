package app.petclinic.controllers;

import app.petclinic.models.Owner;
import app.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController
{
    private static final  String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService)
    {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder)
    {
        dataBinder.setDisallowedFields("id");
    }


    @RequestMapping({"/find"})
    public String findOwners(Model model)
    {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model)
    {
        if (owner.getLname() == null)
        {
            owner.setLname("");
        }

        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLname() + "%");

        if (results.isEmpty())
        {
            result.rejectValue("lname", "notFound", "not found");

            return "owners/findOwners";
        } else if(results.size() == 1)
        {
            owner = results.iterator().next();
            return "redirect:/owners/" + owner.getId();
        } else
        {
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    @GetMapping("/{id}")
    public ModelAndView serveOwner(@PathVariable Long id)
    {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");

        mav.addObject(ownerService.findById(id));

        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model)
    {
        model.addAttribute("owner", Owner.builder().build());

        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result)
    {
        if (result.hasErrors())
        {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else
        {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/{id}/edit")
    public String initUpdateOwnerForm(@PathVariable Long id, Model model)
    {
        model.addAttribute("owner", ownerService.findById(id));

        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{id}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable Long id)
    {
        if (result.hasErrors())
        {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else
        {
            owner.setId(id);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }
}
