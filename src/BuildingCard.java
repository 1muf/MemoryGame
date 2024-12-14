
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
 *         This class represents a "Building" type card in a memory game and
 *         manages its properties
 *         and behaviors, including the color associated with Building cards.
 */
import java.awt.Color;

public class BuildingCard extends Card
{
	public BuildingCard(String value)
	{
		super(value, "Building"); // All BuildingCards have the "Building" type
	}

	@Override
	public Color getColor()
	{
		return Color.ORANGE; // Color for Building cards
	}
}
