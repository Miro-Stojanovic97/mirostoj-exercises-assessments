package pets.data;

import pets.models.Pet;

import java.util.List;

public interface PetRepository {
    //FINDALL
    //Update
    //DeleteBYID
    //ADD
    //FINDBYID
    List<Pet> findAll();

    boolean update(Pet pet);

    boolean deleteById(int petId);

    Pet add(Pet pet);

    Pet findById(int petId);


}
