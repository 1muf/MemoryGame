public class Card {
    private String value;
    private boolean faceUp;

    public Card(String value) {
        this.value = value;
        this.faceUp = false; // Initially, the card is face down
    }

    public String getValue() {
        return value;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void flip() {
        faceUp = !faceUp; // Toggle the face-up state
    }
}
