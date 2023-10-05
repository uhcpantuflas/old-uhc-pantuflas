package com.github.gorkiiuss.uhcpantuflas.teams.exceptions;

/**
 * The UHCTeamException class is a custom exception that can be thrown to indicate errors related to UHC teams.
 *
 * @version 0.0.1-ALPHA.0
 * @since 05/10/2023-ALPHA.0
 */
public class UHCTeamException extends Exception {
    /**
     * Constructs a new UHCTeamException with the specified error message.
     *
     * @param message The error message to associate with this exception.
     */
    public UHCTeamException(String message) {
        super(message);
    }
}
