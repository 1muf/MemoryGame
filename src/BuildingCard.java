import java.awt.Color;

public class BuildingCard extends Card {
    public BuildingCard(String value) {
        super(value, "Building"); // All BuildingCards have the "Building" type
    }

    @Override
    public Color getColor() {
        return Color.ORANGE; // Color for Building cards
    }
}
