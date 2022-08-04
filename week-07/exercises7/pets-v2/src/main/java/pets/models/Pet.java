package pets.models;

public class Pet {

    private int PetId;
    private String name;
    private String type;

    public Pet(int petId, String name, String type) {
        PetId = petId;
        this.name = name;
        this.type = type;
    }

    public Pet() {
        //empty constructor to use for setters
    }

    public int getPetId() {
        return PetId;
    }

    public void setPetId(int petId) {
        PetId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "PetId=" + PetId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pet pet = (Pet) o;

        if (PetId != pet.PetId) return false;
        if (!name.equals(pet.name)) return false;
        return type.equals(pet.type);
    }

    @Override
    public int hashCode() {
        int result = PetId;
        result = 31 * result + name.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
