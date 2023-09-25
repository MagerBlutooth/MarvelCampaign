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
    opens adventure.view;
    opens adventure.controller;
    opens adventure.model;
}