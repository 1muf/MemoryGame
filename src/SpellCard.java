
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
 *         This class represents a SpellCard, which is a specific type of card
 *         in the game.
 *         It extends the Card class and has a "Spell" type. The card's color is
 *         set to cyan,
 *         distinguishing it from other card types. It provides functionality to
 *         retrieve the color
 *         associated with the SpellCard type.
 */
import java.awt.Color;

public class SpellCard extends Card
{
	public SpellCard(String value)
	{
		super(value, "Spell"); // All SpellCards have the "Spell" type
	}

	@Override
	public Color getColor()
	{
		return Color.CYAN; // Color for Spell cards
	}
}
