package lk.ijse.petClinic.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.petClinic.dto.PetDetailsDTO;
import lk.ijse.petClinic.dto.PetOwnerDetailsDTO;
import lk.ijse.petClinic.service.ServiceFactory;
import lk.ijse.petClinic.service.custom.PetService;
import lk.ijse.petClinic.service.exception.DuplicateException;
import lk.ijse.petClinic.view.tm.PetDetailsTM;

public class PetDetailFormController {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<PetDetailsTM, String> colContact;

    @FXML
    private TableColumn<PetDetailsTM, String> colOwnerId;

    @FXML
    private TableColumn<PetDetailsTM, String> colOwnerName;

    @FXML
    private TableColumn<PetDetailsTM, String> colPetId;

    @FXML
    private TableColumn<PetDetailsTM, String> colPetName;

    @FXML
    private TableView<PetDetailsTM> tblPetDetail;

    @FXML
    private TextField txtOwnerAddress;

    @FXML
    private TextField txtOwnerContact;

    @FXML
    private TextField txtOwnerId;

    @FXML
    private TextField txtOwnerName;

    @FXML
    private TextField txtPetAge;

    @FXML
    private TextField txtPetBreed;

    @FXML
    private TextField txtPetId;

    @FXML
    private TextField txtPetName;

    private final ObservableList<PetDetailsTM> petDetailsTMS= FXCollections.observableArrayList();
    private final PetService petService= ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.PET);

    public void initialize(){
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        setCellFactory();
        refreshTable();
    }

    private void refreshTable() {
        petDetailsTMS.clear();
        petDetailsTMS.addAll(petService.getAllPetDetails());
        tblPetDetail.setItems(petDetailsTMS);
    }

    private void setCellFactory() {
        colPetId.setCellValueFactory(new PropertyValueFactory<>("petId"));
        colPetName.setCellValueFactory(new PropertyValueFactory<>("petName"));
        colOwnerId.setCellValueFactory(new PropertyValueFactory<>("ownerId"));
        colOwnerName.setCellValueFactory(new PropertyValueFactory<>("ownerName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String ownerId=txtOwnerId.getText();
        String ownerName=txtOwnerName.getText();
        String ownerAddress=txtOwnerAddress.getText();
        String ownerContact=txtOwnerContact.getText();
        String petId=txtPetId.getText();
        String petName=txtPetName.getText();
        double petAge=Double.parseDouble(txtPetAge.getText());
        String petBreed=txtPetBreed.getText();

        PetOwnerDetailsDTO petOwnerDetailsDTO = new PetOwnerDetailsDTO(ownerId,ownerName,ownerAddress,ownerContact);
        PetDetailsDTO petDetailsDTO = new PetDetailsDTO(petId,ownerId,petName,petAge,petBreed,petOwnerDetailsDTO);
        try {
            if (petService.savePetDetails(petDetailsDTO)) {
                refreshTable();
                clearTextFields();
                new Alert(Alert.AlertType.INFORMATION,"Successful Added!").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Adding Unsuccessful !").show();
            }
        }catch (DuplicateException e){
            txtPetId.selectAll();
            txtPetId.requestFocus();
            new Alert(Alert.AlertType.WARNING,e.getMessage()).show();
        }
    }

    private void clearTextFields() {
        txtOwnerId.setText(null);
        txtOwnerName.setText(null);
        txtOwnerAddress.setText(null);
        txtOwnerContact.setText(null);
        txtPetId.setText(null);
        txtPetName.setText(null);
        txtPetAge.setText(null);
        txtPetBreed.setText(null);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        PetDetailsTM petDetailsTM = tblPetDetail.getSelectionModel().getSelectedItem();
        if (petService.deletePetDetails(petDetailsTM)){
            refreshTable();
            clearTextFields();
            new Alert(Alert.AlertType.INFORMATION,"Successful Deleted!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Delete Unsuccessful !").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {


        String ownerId=txtOwnerId.getText();
        String ownerName=txtOwnerName.getText();
        String ownerAddress=txtOwnerAddress.getText();
        String ownerContact=txtOwnerContact.getText();
        String petId=txtPetId.getText();
        String petName=txtPetName.getText();
        double petAge=Double.parseDouble(txtPetAge.getText());
        String petBreed=txtPetBreed.getText();

        PetOwnerDetailsDTO petOwnerDetailsDTO = new PetOwnerDetailsDTO(ownerId,ownerName,ownerAddress,ownerContact);
        PetDetailsDTO petDetailsDTO = new PetDetailsDTO(petId,ownerId,petName,petAge,petBreed,petOwnerDetailsDTO);
        if (petService.updatePetDetails(petDetailsDTO)) {
            btnUpdate.setDisable(true);
            btnAdd.setDisable(false);
            txtPetId.setDisable(false);
            txtOwnerId.setDisable(false);
            refreshTable();
            clearTextFields();
            new Alert(Alert.AlertType.INFORMATION,"Successful Updated !").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Updating Unsuccessful !").show();
        }
    }

    @FXML
    public void tblActon() {
        if (tblPetDetail.getSelectionModel()==null || tblPetDetail.getSelectionModel().isEmpty()){
            return;
        }
        PetDetailsTM petDetailsTM = tblPetDetail.getSelectionModel().getSelectedItem();
        PetDetailsDTO petDetailsDTO = petService.getPetDetailsDTO(petDetailsTM.getPetId());
        txtOwnerId.setText(petDetailsTM.getOwnerId());
        txtOwnerName.setText(petDetailsTM.getPetName());
        txtOwnerAddress.setText(petDetailsDTO.getPetOwnerDetailsDTO().getAddress());
        txtOwnerContact.setText(petDetailsDTO.getPetOwnerDetailsDTO().getTelNumber());
        txtPetId.setText(petDetailsTM.getPetId());
        txtPetName.setText(petDetailsTM.getPetName());
        txtPetAge.setText(String.valueOf(petDetailsDTO.getPetAge()));
        txtPetBreed.setText(petDetailsDTO.getPetBreed());

        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
        btnAdd.setDisable(true);
        txtPetId.setDisable(true);
        txtOwnerId.setDisable(true);

    }
}
