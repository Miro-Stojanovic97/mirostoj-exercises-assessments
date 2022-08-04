package pets.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pets.domain.PetService;
import pets.domain.Result;
import pets.domain.ResultType;
import pets.models.Pet;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://initial-domain.com"})
@RequestMapping("/pets")
public class PetController {

    private final PetService service;

    //auto-injecting
    public PetController(PetService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pet> findAll() {
        return service.findAll();
    }

    @GetMapping("/{petId}")
    public ResponseEntity<Pet> findById(@PathVariable int petId) {
        Pet pet = service.findById(petId);
        if(pet == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity<Pet> add(@RequestBody Pet pet) {
       Result<Pet> result = service.add(pet);
       if(result.getType() == ResultType.INVALID) {
           return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
       }
       return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @PutMapping("/{petId}")
    public ResponseEntity<Void> update(@PathVariable int petId, @RequestBody Pet pet) {
        // check for ids
        if(petId != pet.getPetId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Result<Pet> result = service.update(pet);
        if(result.getType() == ResultType.INVALID) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (result.getType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<Void> delete(@PathVariable int petId) {
        if(service.deleteById(petId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




}
