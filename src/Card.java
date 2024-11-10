import java.awt.Color;

public abstract class Card {
    private String value;
    private boolean faceUp;
    private String type; // Field to store the type of the card (e.g., "Troop", "Spell")

    public Card(String value, String type) {
        this.value = value;
        this.faceUp = false;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void flip() {
        faceUp = !faceUp;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public String getType() {
        return type;
    }

    public abstract Color getColor(); // Abstract method to define color for each card type
}
