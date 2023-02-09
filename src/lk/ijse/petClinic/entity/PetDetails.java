package lk.ijse.petClinic.entity;

public class PetDetails implements SuperEntity{
    private String petId;
    private String ownerId;
    private String petName;
    private double petAge;
    private String petBreed;

    public PetDetails() {
    }

    public PetDetails(String petId, String ownerId, String petName, double petAge, String petBreed) {
        this.setPetId(petId);
        this.setOwnerId(ownerId);
        this.setPetName(petName);
        this.setPetAge(petAge);
        this.setPetBreed(petBreed);
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public double getPetAge() {
        return petAge;
    }

    public void setPetAge(double petAge) {
        this.petAge = petAge;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }
}
