package lk.ijse.petClinic.view.tm;

public class PetDetailsTM {
    private String petId;
    private String petName;
    private String ownerId;
    private String ownerName;
    private String contact;

    public PetDetailsTM() {
    }

    public PetDetailsTM(String petId, String petName, String ownerId, String ownerName, String contact) {
        this.petId = petId;
        this.petName = petName;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.contact = contact;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "PetDetailsTM{" +
                "petId='" + petId + '\'' +
                ", petName='" + petName + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
