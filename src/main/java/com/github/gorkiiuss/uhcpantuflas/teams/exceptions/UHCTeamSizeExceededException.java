package com.github.gorkiiuss.uhcpantuflas.teams.exceptions;

public class UHCTeamSizeExceededException extends UHCTeamException {
    private static final String MSG = "Team size limit exceeded!";

    public UHCTeamSizeExceededException() {
        super(MSG);
    }
}
