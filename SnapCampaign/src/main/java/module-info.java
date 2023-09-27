module SnapCampaign {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires java.logging;
    requires com.opencsv;

    exports campaign.view;

    opens campaign.view;
    exports campaign.view.button;
    opens campaign.view.button;
    exports campaign.view.pane;
    exports campaign.view.manager;
    opens campaign.view.manager;
    opens campaign.view.pane;
    exports campaign.view.node;
    opens campaign.view.node;
    exports campaign.view.thing;
    opens campaign.view.thing;
    exports campaign.view.node.editor;
    opens campaign.view.node.editor;
    exports campaign.controller;
    opens campaign.controller;
    exports campaign.controller.editor;
    opens campaign.controller.editor;
    exports campaign.model.constants;
    opens campaign.model.constants;
    exports campaign.model.thing;
    opens campaign.model.thing;
    exports campaign.model.sortFilter;
    opens campaign.model.sortFilter;
    exports campaign.model.database;
    opens campaign.model.database;
    exports campaign.controller.view;
    opens campaign.controller.view;
    exports campaign.controller.node;
    opens campaign.controller.node;
    exports campaign.controller.grid;
    opens campaign.controller.grid;
    exports campaign.view.menu;
    opens campaign.view.menu;
    exports campaign.view.node.control;
    opens campaign.view.node.control;
    exports campaign.view.dragdrop;
    opens campaign.view.dragdrop;
    exports campaign.controller.context;
    opens campaign.controller.context;
    opens campaign.view.dialog;
    exports campaign.view.dialog;
    exports campaign.view.main;
    opens campaign.view.main;
    exports campaign.view.fxml;
    opens campaign.view.fxml;
    exports campaign.model.logger;
    exports campaign.model.factory;
    exports campaign.view.pane.manager;
    exports campaign.model.helper;
    exports campaign.view.pane.editor;
    exports campaign.view.grabber;
}