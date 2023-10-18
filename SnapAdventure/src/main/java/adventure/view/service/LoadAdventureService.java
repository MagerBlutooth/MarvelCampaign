package adventure.view.service;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoadAdventureService extends Application {
    private final Button runButton = new Button("Run");
    private final Label label = new Label();

    private final Service service = new Service() {
        @Override
        protected Task createTask() {
            return new Task<Void>() {
                @Override protected Void call() throws Exception {
                    // do stuff
                    Thread.sleep(5000);
                    return null;
                }
            };
        }
    };

    @Override public void start(Stage stage) {
        VBox layout = createLayout();

        Scene scene = new Scene(layout, 100, 80);
        stage.setScene(scene);

        bindUIandService(stage);

        stage.show();
    }

    private void bindUIandService(Stage stage) {
        label.textProperty()
                .bind(
                        service.stateProperty().asString()
                );

        stage.getScene()
                .getRoot()
                .cursorProperty()
                .bind(
                        Bindings
                                .when(service.runningProperty())
                                .then(Cursor.WAIT)
                                .otherwise(Cursor.DEFAULT)
                );

        runButton
                .disableProperty()
                .bind(
                        service.runningProperty()
                );

        runButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                service.restart();
            }
        });
    }

    private VBox createLayout() {
        VBox layout = new VBox(10);

        layout.getChildren().setAll(runButton, label);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        return layout;
    }

    public static void main(String[] args) {
        launch(args);
    }
}