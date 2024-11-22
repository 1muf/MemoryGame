import java.awt.Color;

public class ResourceCard extends Card {
    public ResourceCard(String value) {
        super(value, "Resource"); // All ResourceCards have the "Resource" type
    }

    @Override
    public Color getColor() {
        return Color.YELLOW; // Yellow color for Resource cards
    }
}
