module SnapAdventure {
    requires java.base;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.logging;
    requires com.opencsv;
    requires SnapCampaign;
    requires javafx.controls;

    exports adventure.view;
    exports adventure.controller;
    exports adventure.model;
    exports adventure.controller.manager;
    exports adventure.view.fxml;
    exports adventure.view.node;
    exports adventure.view.manager;
    exports adventure.view.pane;
    exports adventure.view.popup;
    exports adventure.model.adventure;
    opens adventure.view;
    opens adventure.view.fxml;
    opens adventure.view.node;
    opens adventure.controller.manager;
    opens adventure.controller;
    opens adventure.model;
    opens adventure.model.adventure;
    opens adventure.view.pane;
    opens fxml_adventure;
}