public enum CardType {
    SPELL(2),   
    TROOP(3),   
    BUILDING(4);

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
            default:
                return super.toString(); // Default case if needed
        }
    }
}
