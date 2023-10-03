package com.github.gorkiiuss.uhcpantuflas.config.exceptions;

/**
 * An exception thrown when attempting to access or modify an unknown configuration section.
 *
 * @version 0.0.1-ALPHA.0
 * @since 03/10/2023-ALPHA.0
 */
public class UHCConfigUnknownSectionException extends UHCConfigException {
    private static final String MSG = "Section $ does not exist in the configuration file!";
    public UHCConfigUnknownSectionException(String section) {
        super(MSG.replace("$", section));
    }
}
