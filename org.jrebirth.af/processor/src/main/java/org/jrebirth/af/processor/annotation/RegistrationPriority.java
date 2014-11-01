package org.jrebirth.af.processor.annotation;

public enum RegistrationPriority {
    None(0),
    Lowest(1),
    Lower(2),
    Low(3),
    Normal(4),
    High(5),
    Higher(6),
    Highest(7),
    Ultimate(8);

    /** The level. */
    private final int level;

    /**
     * Instantiates a new registration priority.
     *
     * @param level the level
     */
    private RegistrationPriority(final int level) {
        this.level = level;
    }

    /**
     * Gets the level.
     *
     * @return the level
     */
    public int getLevel() {
        return this.level;
    }
}
