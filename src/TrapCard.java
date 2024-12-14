
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
 *         This class represents a TrapCard, which is a specific type of card in
 *         the game.
 *         It extends the Card class and has a "Trap" type. The card's color is
 *         set to red,
 *         distinguishing it from other card types. It provides functionality to
 *         retrieve the color
 *         associated with the TrapCard type.
 */

import java.awt.Color;

public class TrapCard extends Card
{
	public TrapCard(String value)
	{
		super(value, "Trap"); // All TrapCards have the "Trap" type
	}

	@Override
	public Color getColor()
	{
		return Color.RED; // Red color for Trap cards
	}
}
