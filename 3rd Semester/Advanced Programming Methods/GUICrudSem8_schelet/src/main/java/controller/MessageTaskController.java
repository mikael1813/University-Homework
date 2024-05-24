package controller;


import domain.MessageTask;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import services.MessageTaskService;
import utils.events.MessageTaskChangeEvent;
import utils.events.TaskStatusEvent;
import utils.observer.Observer;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MessageTaskController implements Observer<MessageTaskChangeEvent> {
    MessageTaskService service;
    ObservableList<MessageTask> model = FXCollections.observableArrayList();


    @FXML
    TableView<MessageTask> tableView;
    @FXML
    TableColumn<MessageTask, String> tableColumnDesc;
    @FXML
    TableColumn<MessageTask, String> tableColumnFrom;
    @FXML
    TableColumn<MessageTask, String> tableColumnTo;
    @FXML
    TableColumn<MessageTask, String> tableColumnData;

    public void setMessageTaskService(MessageTaskService messageTaskService) {
        service = messageTaskService;
        service.addObserver(this);
        initModel();
    }

    @FXML
    public void initialize() {
        // TODO
        tableColumnDesc.setCellValueFactory(new PropertyValueFactory<MessageTask, String>("description"));
        tableColumnFrom.setCellValueFactory(new PropertyValueFactory<MessageTask, String>("from"));
        tableColumnTo.setCellValueFactory(new PropertyValueFactory<MessageTask, String>("to"));
        tableColumnData.setCellValueFactory(new PropertyValueFactory<MessageTask, String>("date"));
        tableView.setItems(model);
    }

    private void initModel() {
        // TODO
        Iterable<MessageTask> messages = service.getAll();
        List<MessageTask> messageTaskList = StreamSupport.stream(messages.spliterator(), false)
                .collect(Collectors.toList());
        model.setAll(messageTaskList);
    }

    @Override
    public void update(MessageTaskChangeEvent messageTaskChangeEvent) {
        initModel();
    }

    public void handleDeleteMessage(ActionEvent actionEvent) {
        MessageTask messageTask = tableView.getSelectionModel().getSelectedItem();

        if (messageTask != null) {
            service.deleteMessageTask(messageTask);
        } else {
            MessageAlert.showErrorMessage(null, "Nu ati selectat nimic");
        }
    }

    @FXML
    public void handleUpdateMessage(ActionEvent ev) {
        MessageTask messageTask = tableView.getSelectionModel().getSelectedItem();

        if (messageTask != null) {
            showMessageTaskEditDialog(messageTask);
        } else {
            MessageAlert.showErrorMessage(null, "Nu ati selectat nimic");
        }
    }

    @FXML
    public void handleAddMessage(ActionEvent ev) {
        showMessageTaskEditDialog(null);
    }

    public void showMessageTaskEditDialog(MessageTask messageTask) {
        try {
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/editMessageTaskView.fxml"));

            AnchorPane root = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Message");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            EditMessageTaskController editMessageViewController = loader.getController();
            editMessageViewController.setService(service, dialogStage, messageTask);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
