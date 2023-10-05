package com.github.gorkiiuss.uhcpantuflas.teams.exceptions;

public class UnknownUHCTeamException extends UHCTeamException {
    private static final String MSG = "Team $1 does not exist!";
    public UnknownUHCTeamException(String teamName) {
        super(MSG.replace("$1", teamName));
    }
}
