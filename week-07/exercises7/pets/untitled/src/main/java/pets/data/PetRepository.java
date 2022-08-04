package pets.data;

import pets.models.Pet;

import java.util.List;

public interface PetRepository {

    //CRUD

    List<Pet> findAll();

    Pet findById(int petId);


    boolean update(Pet pet);

    boolean deleteById(int petId);

    Pet add(Pet pet);



}
