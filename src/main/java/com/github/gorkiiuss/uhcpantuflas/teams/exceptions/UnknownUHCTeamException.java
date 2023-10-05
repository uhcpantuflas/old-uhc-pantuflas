package com.github.gorkiiuss.uhcpantuflas.teams.exceptions;

/**
 * The UnknownUHCTeamException class represents an exception thrown when attempting to access or manipulate a team
 * that does not exist.
 *
 * @version 0.0.1-ALPHA.0
 * @since 05/10/2023-ALPHA.0
 */
public class UnknownUHCTeamException extends UHCTeamException {
    private static final String MSG = "Team $1 does not exist!";

    /**
     * Constructs an UnknownUHCTeamException with a customized error message.
     *
     * @param teamName The name of the non-existent team.
     */
    public UnknownUHCTeamException(String teamName) {
        super(MSG.replace("$1", teamName));
    }
}
