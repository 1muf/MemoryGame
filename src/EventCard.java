import java.awt.Color;

public class EventCard extends Card {
    public EventCard(String value) {
        super(value, "Event"); // All EventCards have the "Event" type
    }

    @Override
    public Color getColor() {
        return Color.BLUE; // Blue color for Event cards
    }
}
