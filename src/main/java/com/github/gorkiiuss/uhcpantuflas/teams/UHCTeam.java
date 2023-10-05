package com.github.gorkiiuss.uhcpantuflas.teams;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class UHCTeam {
    private final Set<String> members = new HashSet<>();

    public UHCTeam() {

    }

    public boolean hasMember(String playerName) {
        return members.contains(playerName);
    }

    public int getSize() {
        return members.size();
    }

    public void addMembers(String[] teamMembers) throws UHCTeamError {
        int alreadyTeamMembers = (int) Arrays.stream(teamMembers).filter(members::contains).count();
        if (teamMembers.length + members.size() - alreadyTeamMembers > TeamManager.get().getTeamsSize())
            throw new UHCTeamError();

        members.addAll(List.of(teamMembers));
    }

    public String[] getMembers() {
        return members.toArray(new String[0]);
    }
}
