
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
 *         This class represents a PetCard, which is a specific type of card in
 *         the game.
 *         It extends the Card class and has a "Pet" type. The card's color is
 *         set to pink,
 *         differentiating it from other card types. It provides functionality
 *         to retrieve
 *         the color associated with the PetCard type.
 */

import java.awt.Color;

public class PetCard extends Card
{
	public PetCard(String value)
	{
		super(value, "Pet"); // All PetCards have the "Pet" type
	}

	@Override
	public Color getColor()
	{
		return new Color(255, 182, 193); // Pink color for Pet cards
	}
}
