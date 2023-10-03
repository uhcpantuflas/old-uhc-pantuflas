package com.github.gorkiiuss.uhcpantuflas.config.exceptions;

/**
 * An exception thrown when a configuration option has a value of the wrong data type.
 *
 * @version 0.0.1-ALPHA.0
 * @since 03/10/2023-ALPHA.0
 */
public class UHCConfigWrongValueTypeException extends UHCConfigException {
    private static final String MSG = "Option $1 value must be of type $2!";
    public UHCConfigWrongValueTypeException(String option, Class<?> valueType) {
        super(MSG.replace("$1", option).replace("$2", valueType.getName()));
    }
}
