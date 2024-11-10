import java.awt.Color;

public class TroopCard extends Card {
    public TroopCard(String value) {
        super(value, "Troop"); // All TroopCards have the "Troop" type
    }

    @Override
    public Color getColor() {
        return Color.GREEN; // Color for Troop cards
    }
}
