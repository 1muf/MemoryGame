public enum CardType {
    SPELL(2),   
    TROOP(3),   
    BUILDING(4),
    HERO(5),      // Hero card
    PET(1),       // Pet card
    TRAP(6),      // Trap card
    RESOURCE(2),  // Resource card
    EVENT(3);     // Event card

    private final int points;

    CardType(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    // Custom toString() method to display more user-friendly names
    @Override
    public String toString() {
        switch(this) {
            case SPELL:
                return "Spell Card";
            case TROOP:
                return "Troop Card";
            case BUILDING:
                return "Building Card";
            case HERO:
                return "Hero Card";
            case PET:
                return "Pet Card";
            case TRAP:
                return "Trap Card";
            case RESOURCE:
                return "Resource Card";
            case EVENT:
                return "Event Card";
            default:
                return super.toString(); // Default case if needed
        }
    }
}
