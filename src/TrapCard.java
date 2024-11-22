import java.awt.Color;

public class TrapCard extends Card {
    public TrapCard(String value) {
        super(value, "Trap"); // All TrapCards have the "Trap" type
    }

    @Override
    public Color getColor() {
        return Color.RED; // Red color for Trap cards
    }
}
