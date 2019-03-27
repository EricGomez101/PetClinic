package app.petclinic.services.springdatajpa;

import app.petclinic.models.Owner;
import app.petclinic.repositories.OwnerRepository;
import app.petclinic.repositories.PetRepository;
import app.petclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerJPAServiceTest
{
    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerJPAService ownerService;

    final String LAST_NAME = "Smith";

    private Owner returnOwner;


    @BeforeEach
    void setUp()
    {
        returnOwner = Owner.builder().id(1L).lname(LAST_NAME).build();
    }

    @Test
    void findByLastName()
    {


        when(ownerRepository.findByLname(any())).thenReturn(returnOwner);

        Owner smith = ownerService.findByLastName(LAST_NAME);

        assertNotNull(smith);

        assertEquals(LAST_NAME, smith.getLname());

        verify(ownerRepository).findByLname(any());


    }

    @Test
    void findAll()
    {
        Set<Owner> returnOwners = new HashSet<>();

        returnOwners.add(Owner.builder().id(1L).build());
        returnOwners.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(returnOwners);

        Set<Owner> owners = ownerService.findAll();

        assertNotNull(owners);

        assertEquals(2, owners.size());
    }

    @Test
    void findById()
    {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner owner = ownerService.findById(1L);

        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound()
    {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = ownerService.findById(1L);

        assertNull(owner);
    }

    @Test
    void save()
    {
        Owner ownerToSave = Owner.builder().id(1L).build();

        when(ownerRepository.save(any())).thenReturn(returnOwner);

        Owner savedOwner = ownerService.save(ownerToSave);

        assertNotNull(savedOwner);

        verify(ownerRepository, times(1)).save(any());
    }

    @Test
    void delete()
    {
        ownerService.delete(returnOwner);

        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById()
    {
        ownerService.deleteById(1L);

        verify(ownerRepository).deleteById(anyLong());
    }
}