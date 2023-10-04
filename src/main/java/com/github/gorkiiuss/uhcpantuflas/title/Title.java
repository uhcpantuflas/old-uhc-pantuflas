package com.github.gorkiiuss.uhcpantuflas.title;

/**
 * The Title class represents a title message with optional display duration and fade-in/fade-out effects.
 * It is used to display titles on the player's screen.
 * @version 0.0.1-ALPHA.0
 * @since 02/10/2023-ALPHA-0
 */
public class Title {
    private final String title;
    private final String subtitle;
    private final int fadeIn;
    private final int stay;
    private final int fadeOut;

    /**
     * Constructs a Title object with the specified title, subtitle, and display parameters.
     *
     * @param title    The main title text.
     * @param subtitle The subtitle text.
     * @param fadeIn   The duration (in ticks) for the fade-in effect.
     * @param stay     The duration (in ticks) for the title to stay on the screen.
     * @param fadeOut  The duration (in ticks) for the fade-out effect.
     */
    public Title(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    /**
     * Get the main title text.
     *
     * @return The main title text.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the subtitle text.
     *
     * @return The subtitle text.
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * Get the duration (in ticks) for the fade-in effect.
     *
     * @return The fade-in duration.
     */
    public int getFadeIn() {
        return fadeIn;
    }

    /**
     * Get the duration (in ticks) for the title to stay on the screen.
     *
     * @return The stay duration.
     */
    public int getStay() {
        return stay;
    }

    /**
     * Get the duration (in ticks) for the fade-out effect.
     *
     * @return The fade-out duration.
     */
    public int getFadeOut() {
        return fadeOut;
    }

    @Override
    public String toString() {
        return "Title{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", fadeIn=" + fadeIn +
                ", stay=" + stay +
                ", fadeOut=" + fadeOut +
                '}';
    }
}
