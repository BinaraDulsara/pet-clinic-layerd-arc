package lk.ijse.petClinic.dto;


public class PetOwnerDetailsDTO {
    private String ownerId;
    private String name;
    private String address;
    private String telNumber;

    public PetOwnerDetailsDTO() {
    }

    public PetOwnerDetailsDTO(String ownerId, String name, String address, String telNumber) {
        this.setOwnerId(ownerId);
        this.setName(name);
        this.setAddress(address);
        this.setTelNumber(telNumber);
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }
}
