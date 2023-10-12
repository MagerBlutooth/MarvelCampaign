module SnapAdventure {
    requires java.base;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.logging;
    requires com.opencsv;
    requires SnapMain;
    requires javafx.controls;

    exports adventure.view;
    exports adventure.controller;
    exports adventure.model;
    exports adventure.model.adventure;
    exports adventure.model.sorter;
    exports adventure.model.thing;
    opens adventure.model.thing;
    exports adventure.controller.manager;
    exports adventure.view.fxml;
    exports adventure.view.node;
    exports adventure.view.manager;
    exports adventure.view.pane;
    exports adventure.view.popup;

    opens adventure.view;
    opens adventure.view.fxml;
    opens adventure.view.node;
    opens adventure.view.popup;
    opens adventure.controller.manager;
    opens adventure.controller;
    opens adventure.model;
    opens adventure.model.adventure;
    opens adventure.model.sorter;
    opens adventure.view.pane;
    opens fxml_adventure;
    exports adventure.model.stats;
    opens adventure.model.stats;

}