package com.github.gorkiiuss.uhcpantuflas.config.exceptions;

public class UHCConfigWrongValueException extends UHCConfigException {
    private static final String MSG = "Wrong value $1 for option $2 in section $3, reason: $4!";

    public UHCConfigWrongValueException(String value, String optionName, String sectionName, String reason) {
        super(
                MSG.replace("$1", value)
                        .replace("$2", optionName)
                        .replace("$3", sectionName)
                        .replace("$4", reason)
        );
    }
}
