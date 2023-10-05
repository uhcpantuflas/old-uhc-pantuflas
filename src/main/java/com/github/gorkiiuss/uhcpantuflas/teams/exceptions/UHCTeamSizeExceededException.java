package com.github.gorkiiuss.uhcpantuflas.teams.exceptions;

/**
 * The UHCTeamSizeExceededException class represents an exception thrown when the team size limit is exceeded.
 * This exception typically occurs when trying to add a player to a team that has reached its maximum capacity.
 *
 * @version 0.0.1-ALPHA.0
 * @since 05/10/2023-ALPHA.0
 */
public class UHCTeamSizeExceededException extends UHCTeamException {
    private static final String MSG = "Team size limit exceeded!";

    /**
     * Constructs a UHCTeamSizeExceededException with a default error message.
     */
    public UHCTeamSizeExceededException() {
        super(MSG);
    }
}
