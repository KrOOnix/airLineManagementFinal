package ba.reservation.airlinemanagement.gui.admin.plane;

import ba.reservation.airlinemanagement.bussines.model.Plane;
import ba.reservation.airlinemanagement.bussines.plane.PlaneService;
import ba.reservation.airlinemanagement.bussines.plane.PlaneServiceFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import java.util.List;


public class PlaneAdminPanel extends VBox {

    private Label titleLabel = new Label("Administracija aviona");
    private ObservableList<Plane> planeObservableList;
    private TableView<Plane> planeTableView = new TableView<>();

    private final TextField codeTextField = new TextField();
    private final TextField numberSeatTextField = new TextField();
    private final TextField namePlaneTextField = new TextField();
    private Button addPlaneButton = new Button("Dodaj");
    private Button deleteButton = new Button("Obriši");
    private final Label messageLabel = new Label();

    public PlaneAdminPanel(){
        titleLabel.setFont(new Font("Arial", 20));
        setSpacing(10);
        setPadding(new Insets(10, 10, 10, 10));
        titleLabel.setStyle("-fx-background-color: yellow;-fx-border-color: black; -fx-text-fill: black;");
        addPlaneButton.setStyle("-fx-background-color: green;-fx-border-color: black; -fx-text-fill: black;-fx-font-weight: bold");
        deleteButton.setStyle("-fx-background-color: red;-fx-border-color: black; -fx-text-fill: black;-fx-font-weight: bold");
        planeTableView.setStyle("-fx-background-color: black;-fx-font-weight: bold;");
        codeTextField.setStyle("-fx-border-color: black;-fx-font-weight: bold");
        numberSeatTextField.setStyle("-fx-border-color: black;-fx-font-weight: bold");
        namePlaneTextField.setStyle("-fx-border-color: black;-fx-font-weight: bold");
        planeTableView.setMaxHeight(200);
        planeTableView.setMaxWidth(650);

        numberSeatTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    numberSeatTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        TableColumn<Plane, String> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<Plane, String>("id"));

        TableColumn<Plane, String> planeCodeColumn = new TableColumn<>("Kod aviona");
        planeCodeColumn.setCellValueFactory(new PropertyValueFactory<Plane, String>("code"));

        TableColumn<Plane, Integer> numberSeatColumn = new TableColumn<>("Broj sjedišta");
        numberSeatColumn.setCellValueFactory(new PropertyValueFactory<Plane, Integer>("numberSeat"));

        TableColumn<Plane, String> planeDestinationColumn = new TableColumn<>("Ime aviona");
        planeDestinationColumn.setCellValueFactory(new PropertyValueFactory<Plane, String>("namePlane"));

        List<Plane> planeList = PlaneServiceFactory.SERVICE.getPlaneService().findAll();
        planeObservableList = FXCollections.observableList(planeList);
        planeTableView.setItems(planeObservableList);
        planeTableView.getColumns().addAll(idColumn,planeCodeColumn,numberSeatColumn,planeDestinationColumn);
        getChildren().addAll(titleLabel,planeTableView, getForm(),messageLabel);
    }

    private HBox getForm(){
        HBox form = new HBox();
        form.setSpacing(5);
        codeTextField.setPromptText("Kod aviona");
        numberSeatTextField.setPromptText("Broj sjedišta u avionu");
        namePlaneTextField.setPromptText("Ime aviona");
        addPlaneButton.setOnAction(this::addPlane);
        deleteButton.setOnAction(this::removePlane);

        form.getChildren().addAll(codeTextField,numberSeatTextField,namePlaneTextField,addPlaneButton,deleteButton);
        return form;
    }

    private void removePlane(ActionEvent actionEvent){
        Plane selectedPlane = planeTableView.getSelectionModel().getSelectedItem();
        PlaneService planeService = PlaneServiceFactory.SERVICE.getPlaneService();
        planeService.removeById(selectedPlane.getId());
        planeObservableList.remove(selectedPlane);
    }

    private void addPlane(ActionEvent actionEvent){

        if (validate()){

            Plane plane = new Plane();
            plane.setCode(codeTextField.getText());
            plane.setNumberSeat(Integer.parseInt(numberSeatTextField.getText()));
            plane.setNamePlane(namePlaneTextField.getText());

            PlaneService planeService = PlaneServiceFactory.SERVICE.getPlaneService();
            planeService.create(plane);
            planeObservableList.add(plane);

            codeTextField.clear();
            numberSeatTextField.clear();
            namePlaneTextField.clear();
        }else {
            messageLabel.setText("Sva polja moraju biti popunjena!");
            messageLabel.setStyle("-fx-text-fill: red;-fx-font-weight: bold ");

        }


    }
    private boolean validate() {
        return !codeTextField.getText().isBlank()
                && !numberSeatTextField.getText().isBlank()
                && !namePlaneTextField.getText().isBlank();

    }

}









