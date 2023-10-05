package com.github.gorkiiuss.uhcpantuflas.teams;

public class UHCTeamError extends Exception {
    private static final String MSG = "Team size limit exceeded!";

    public UHCTeamError() {
        super(MSG);
    }
}
