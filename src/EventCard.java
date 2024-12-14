
/**
 * Lead Author(s):
 * 
 * @author Tyler Wang
 * 
 *         Other contributors:
 *         None
 * 
 *         Version/date: 8.0 / 12/13/2024
 * 
 *         Responsibilities of class:
 *         This class represents an "Event" type card in a memory game and
 *         manages its properties
 *         and behaviors, including the color associated with Event cards.
 */
import java.awt.Color;

public class EventCard extends Card
{
	public EventCard(String value)
	{
		super(value, "Event"); // All EventCards have the "Event" type
	}

	@Override
	public Color getColor()
	{
		return Color.BLUE; // Blue color for Event cards
	}
}
