import java.awt.Color;

public class PetCard extends Card {
    public PetCard(String value) {
        super(value, "Pet"); // All PetCards have the "Pet" type
    }

    @Override
    public Color getColor() {
        return new Color(255, 182, 193); // Pink color for Pet cards
    }
}
