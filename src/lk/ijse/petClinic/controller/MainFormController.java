package lk.ijse.petClinic.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ijse.petClinic.service.ServiceFactory;
import lk.ijse.petClinic.service.custom.PetService;

import java.io.IOException;

public class MainFormController {

    @FXML
    private AnchorPane ancContain;

    @FXML
    private AnchorPane ancMain;

    @FXML
    private Label lblAppoinmentCount;

    @FXML
    private Label lblCustomerCount;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    private final PetService petService=ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.PET);

    public void initialize(){
        loadLabel();
    }

    private void loadLabel() {
        lblCustomerCount.setText(String.valueOf(petService.getAllOwnersCount()));
        lblAppoinmentCount.setText(String.valueOf(petService.getAllAppointmentCount()));
    }

    @FXML
    void btnAppointmentOnAtion(ActionEvent event) {
        initUI(ancContain,"AppointmentForm.fxml");
    }

    @FXML
    void btnBillOnAction(ActionEvent event) {
        initUI(ancContain,"BillForm.fxml");
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        initUI(ancMain,"MainForm.fxml");
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        initUI(ancMain,"LoginForm.fxml");
    }

    @FXML
    void btnPetDetailsOnAction(ActionEvent event) {
        initUI(ancContain,"petDetailsForm.fxml");
    }

    public void initUI(Pane pane,String location) {
        try {
            pane.getChildren().clear();
            pane.getChildren().add(FXMLLoader.load(getClass().getResource("/lk/ijse/petClinic/view/"+location)));
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Forms error !");
        }
    }
}
