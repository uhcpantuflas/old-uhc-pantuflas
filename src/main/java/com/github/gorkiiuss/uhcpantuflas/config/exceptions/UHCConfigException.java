package com.github.gorkiiuss.uhcpantuflas.config.exceptions;

/**
 * A base exception class for handling configuration-related exceptions in the UHC Pantuflas plugin.
 *
 * @version 0.0.1-ALPHA.0
 * @since 03/10/2023-ALPHA.0
 */
public class UHCConfigException extends Exception {
    public UHCConfigException(String message) {
        super(message);
    }
}
