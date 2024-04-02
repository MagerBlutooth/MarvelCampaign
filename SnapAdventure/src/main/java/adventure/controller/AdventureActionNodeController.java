package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.adventure.Adventure;
import adventure.model.target.ActiveCard;
import adventure.model.target.ActiveCardList;
import adventure.view.pane.AdventureControlPane;
import adventure.view.pane.LogViewPane;
import adventure.view.popup.CardDisplayPopup;
import adventure.view.popup.CardSearchSelectDialog;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import snapMain.model.logger.MLogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AdventureActionNodeController {

    @FXML
    public Button randomCard;
    AdvMainDatabase mainDatabase;
    AdventureControlPane controlPane;
    Adventure adventure;

    MLogger logger = new MLogger(AdventureActionNodeController.class);

    public void initialize(AdvMainDatabase database, Adventure a, AdventureControlPane cPane) {
        mainDatabase = database;
        adventure = a;
        controlPane = cPane;
    }

    @FXML
    public void draftCard()
    {
        controlPane.draftCard();
    }

    @FXML
    public void generateCards()
    {
        controlPane.generateCards();
    }

    @FXML
    public void searchCard()
    {
        controlPane.searchCard();
    }
    @FXML
    public void randomCard()
    {
        ActiveCardList cards = adventure.getActiveCards();
        ActiveCard card = cards.getRandom();
        CardDisplayPopup popup = new CardDisplayPopup(mainDatabase, card,
                    randomCard.localToScreen(100.0,50.0));
        popup.show();
        popup.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                popup.hide();
            }
        });

    }

    @FXML
    public void showLog()
    {
        File file = getLogFile();
        TextArea logArea = createLogText(file);
        LogViewPane logViewPane = new LogViewPane();
        logViewPane.initialize(logArea, controlPane);
        controlPane.changeScene(logViewPane);
    }

    @FXML
    public void injectCard()
    {
        CardSearchSelectDialog searchSelectDialog = new CardSearchSelectDialog();
        ActiveCardList missingCards = adventure.getMissingCards(mainDatabase);
        if(!missingCards.isEmpty()) {
            searchSelectDialog.initialize(mainDatabase, missingCards, adventure.getTeamCards(),
                    "Add a card to the game",
                    controlPane.getScene().getWindow());
            Optional<ActiveCard> injectedCard = searchSelectDialog.showAndWait();
            if (injectedCard.isPresent()) {
                adventure.injectCard(injectedCard.get());
                adventure.saveAdventure();
            }
        }
        else
            logger.info("Cannot inject cards. No cards remaining to inject.");
    }

    private TextArea createLogText(File f) {
        TextArea logText = new TextArea();
        logText.setWrapText(true);
        logText.setEditable(false);
        logText.setFocusTraversable(false);
        List<String> logStrings = new ArrayList<>();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(f));
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                logStrings.add(text);
            }
            Collections.reverse(logStrings);

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        logText.setText(logStrings.stream().map(Object::toString).collect(Collectors.joining("\n")));
        return logText;
    }

    private File getLogFile() {
        return new File(adventure.getProfile().getLogFile());
    }
}
