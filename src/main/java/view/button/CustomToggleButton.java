package view.button;

import javafx.scene.control.Button;

public class CustomToggleButton extends Button {
    boolean enabled;


    public CustomToggleButton(String name)
    {
        super(name);
        enabled = true;
        highlight();
    }
    public void toggle()
    {
        enabled = !enabled;
        if(enabled)
            highlight();
        else
            lowlight();
    }

    private void lowlight() {
        this.setStyle("-fx-background-color: black;-fx-text-fill: white");
    }

    private void highlight() {
        this.setStyle("-fx-background-color: white;-fx-text-fill: black");
    }

    public Boolean isEnabled() {
        return enabled;
    }
}
