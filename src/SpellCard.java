import java.awt.Color;

public class SpellCard extends Card {
    public SpellCard(String value) {
        super(value, "Spell"); // All SpellCards have the "Spell" type
    }

    @Override
    public Color getColor() {
        return Color.CYAN; // Color for Spell cards
    }
}
