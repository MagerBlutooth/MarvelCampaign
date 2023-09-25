module SnapRecords {
    requires java.base;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.logging;
    requires com.opencsv;
    requires javafx.controls;
    requires SnapCampaign;

    exports records;
    exports records.view;
    exports records.controller;
    exports records.model;
    opens records.view;
    opens records.controller;
    opens records.model;
    exports records.view.fxml;
    opens records.view.fxml;
    opens fxml_record;
}