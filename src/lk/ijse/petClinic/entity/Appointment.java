package lk.ijse.petClinic.entity;

public class Appointment implements SuperEntity {
    private String appId;
    private String ownerId;
    private String petId;
    private String date;

    public Appointment() {

    }

    public Appointment(String appId, String ownerId, String petId, String date) {
        this.setAppId(appId);
        this.setOwnerId(ownerId);
        this.setPetId(petId);
        this.setDate(date);
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
