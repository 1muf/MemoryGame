import java.awt.Color;

public class HeroCard extends Card {
    public HeroCard(String value) {
        super(value, "Hero"); // All HeroCards have the "Hero" type
    }

    @Override
    public Color getColor() {
        return new Color(128, 0, 128); // Purple color for Hero cards
    }
}
