package com.example.todo_list;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ToDoListApp extends Application {

    private ObservableList<String> tasks;
    private ObservableList<String> completedTasks;
    private ListView<String> listView;
    private ListView<String> completedListView;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("To-Do List");

        // Initialize the task lists
        tasks = FXCollections.observableArrayList();
        completedTasks = FXCollections.observableArrayList();
        listView = new ListView<>(tasks);
        completedListView = new ListView<>(completedTasks);

        // Text field for task input
        TextField taskInput = new TextField();
        taskInput.setPromptText("Enter a task");
        taskInput.setPrefWidth(200);

        // Buttons for adding and removing tasks
        Button addButton = new Button("Add Task");
        addButton.setOnAction(e -> addTask(taskInput.getText()));

        Button removeButton = new Button("Remove Task");
        removeButton.setOnAction(e -> removeTask());

        // Button to clear all tasks
        Button clearButton = new Button("Clear All");
        clearButton.setOnAction(e -> tasks.clear());

        // Button to mark task as completed
        Button completeButton = new Button("Complete Task");
        completeButton.setOnAction(e -> markTaskAsCompleted());

        // Layout for buttons
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(addButton, removeButton, completeButton, clearButton);

        // Set up the layout
        VBox inputBox = new VBox(10);
        inputBox.getChildren().addAll(taskInput, buttonBox);

        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10));
        layout.setLeft(inputBox);
        layout.setCenter(listView);
        layout.setRight(completedListView);

        // Set font for all UI components to Arial
        taskInput.setStyle("-fx-font-family: Arial;");
        addButton.setStyle("-fx-font-family: Arial;");
        removeButton.setStyle("-fx-font-family: Arial;");
        completeButton.setStyle("-fx-font-family: Arial;");
        clearButton.setStyle("-fx-font-family: Arial;");
        listView.setStyle("-fx-font-family: Arial;");
        completedListView.setStyle("-fx-font-family: Arial;");

        Scene scene = new Scene(layout, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to add a task
    private void addTask(String task) {
        if (!task.isEmpty()) {
            tasks.add(task);
        }
    }

    // Method to remove the selected task
    private void removeTask() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tasks.remove(selectedIndex);
        }
    }

    // Method to mark the selected task as completed
    private void markTaskAsCompleted() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String task = tasks.get(selectedIndex);
            tasks.remove(selectedIndex);
            completedTasks.add(task);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

