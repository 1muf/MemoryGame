
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
 *         This class represents a TroopCard, which is a specific type of card
 *         in the game.
 *         It extends the Card class and has a "Troop" type. The card's color is
 *         set to green,
 *         distinguishing it from other card types. It provides functionality to
 *         retrieve the color
 *         associated with the TroopCard type.
 */
import java.awt.Color;

public class TroopCard extends Card
{
	public TroopCard(String value)
	{
		super(value, "Troop"); // All TroopCards have the "Troop" type
	}

	@Override
	public Color getColor()
	{
		return Color.GREEN; // Color for Troop cards
	}
}
