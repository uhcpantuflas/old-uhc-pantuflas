package com.github.gorkiiuss.uhcpantuflas.config.exceptions;

/**
 * An exception thrown when attempting to access or modify an unknown configuration option within a section.
 *
 * @version 0.0.1-ALPHA.0
 * @since 03/10/2023-ALPHA.0
 */
public class UHCConfigUnknownOptionException extends UHCConfigException {
    private static final String MSG = "Option $ does not exist in the specified section!";
    public UHCConfigUnknownOptionException(String option) {
        super(MSG.replace("$", option));
    }
}
