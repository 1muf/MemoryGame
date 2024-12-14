
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
 *         This class represents a ResourceCard, which is a specific type of
 *         card in the game.
 *         It extends the Card class and has a "Resource" type. The card's color
 *         is set to yellow,
 *         differentiating it from other card types. It provides functionality
 *         to retrieve the color
 *         associated with the ResourceCard type.
 */
import java.awt.Color;

public class ResourceCard extends Card
{
	public ResourceCard(String value)
	{
		super(value, "Resource"); // All ResourceCards have the "Resource" type
	}

	@Override
	public Color getColor()
	{
		return Color.YELLOW; // Yellow color for Resource cards
	}
}
