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
    exports adventure.view.node;
    opens adventure.view;
    opens adventure.view.node;
    opens adventure.controller.manager;
    opens adventure.controller;
    opens adventure.model;
    opens fxml_adventure;
}