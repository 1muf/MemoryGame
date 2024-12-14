
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
 *         This class extends the Card class and represents a special type of
 *         card, the HeroCard.
 *         It overrides the getColor method to return a purple color specific to
 *         Hero cards.
 *         HeroCards have a predefined "Hero" type and are initialized with a
 *         value when created.
 */
import java.awt.Color;

public class HeroCard extends Card
{
	public HeroCard(String value)
	{
		super(value, "Hero"); // All HeroCards have the "Hero" type
	}

	@Override
	public Color getColor()
	{
		return new Color(128, 0, 128); // Purple color for Hero cards
	}
}
