package com.github.gorkiiuss.uhcpantuflas.config;

import com.github.gorkiiuss.uhcpantuflas.config.exceptions.UHCConfigException;
import com.github.gorkiiuss.uhcpantuflas.config.exceptions.UHCConfigUnknownOptionException;
import com.github.gorkiiuss.uhcpantuflas.config.exceptions.UHCConfigUnknownSectionException;
import com.github.gorkiiuss.uhcpantuflas.config.exceptions.UHCConfigWrongValueTypeException;
import com.github.gorkiiuss.uhcpantuflas.title.Title;
import com.github.gorkiiuss.uhcpantuflas.title.TitleManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Objects;

/**
 * The ConfigurationManager class is responsible for managing the plugin's configuration.
 * It provides methods to initialize and access configuration settings.
 *
 * @version 0.0.1-ALPHA.0
 * @since 03/10/2023-ALPHA.0
 */
public class ConfigurationManager {
    private static ConfigurationManager instance;

    public static final String JOINING_TITLE_KEY = "joining-title";
    public static final String TITLE_KEY = "title";
    public static final String SUBTITLE_KEY = "subtitle";
    public static final String FADE_IN_KEY = "fade-in";
    public static final String STAY_KEY = "stay";
    public static final String FADE_OUT_KEY = "fade-out";

    private FileConfiguration config;

    private ConfigurationManager() {
        // Private constructor to enforce singleton pattern
    }

    /**
     * Get the instance of the ConfigurationManager.
     *
     * @return The ConfigurationManager instance.
     */
    public static synchronized ConfigurationManager get() {
        if (instance == null) instance = new ConfigurationManager();
        return instance;
    }

    /**
     * Initialize the ConfigurationManager with the plugin's configuration.
     *
     * @param config The UHCPantuflas plugin's configuration.
     */
    public void init(FileConfiguration config) {
        this.config = config;
        loadConfig();
    }

    private void loadConfig() {
        // Load a joining title from configuration values
        loadJoiningTitle(Objects.requireNonNull(config.getConfigurationSection(JOINING_TITLE_KEY)));
    }

    /**
     * Create a joining title based on configuration settings and set it using TitleManager.
     *
     * @param joiningTitleSection The configuration section containing joining title settings.
     */
    private void loadJoiningTitle(ConfigurationSection joiningTitleSection) {
        Title joiningTitle = new Title(
                joiningTitleSection.getString(TITLE_KEY, ""),
                joiningTitleSection.getString(SUBTITLE_KEY, ""),
                joiningTitleSection.getInt(FADE_IN_KEY, 0),
                joiningTitleSection.getInt(STAY_KEY, 0),
                joiningTitleSection.getInt(FADE_OUT_KEY, 0)
        );

        TitleManager.get().setJoiningTitle(joiningTitle);
    }

    /**
     * Set a configuration option in the specified section to the provided value.
     *
     * @param sectionName The name of the configuration section.
     * @param optionName  The name of the configuration option.
     * @param value       The new value for the configuration option.
     * @throws UHCConfigException if there's an issue with the configuration.
     */
    public void set(String sectionName, String optionName, String value) throws UHCConfigException {
        if (!config.isConfigurationSection(sectionName)) throw new UHCConfigUnknownSectionException(sectionName);
        ConfigurationSection section = Objects.requireNonNull(config.getConfigurationSection(sectionName));

        if (!section.contains(optionName)) throw new UHCConfigUnknownOptionException(optionName);

        Object lastValue = section.get(optionName);
        Class<?> valueType = lastValue == null? String.class: lastValue.getClass();
        Object parsedValue = parseValue(valueType, optionName, value);

        Objects.requireNonNull(config.getConfigurationSection(sectionName)).set(optionName, parsedValue);

        loadConfig();
    }

    private Object parseValue(Class<?> valueType, String option, String value) throws UHCConfigWrongValueTypeException {
        try {
            if (valueType == Integer.class) {
                return Integer.parseInt(value);
            } else if (value.charAt(0) == '\"' && value.charAt(value.length() - 1) == '\"') { // Is String
                return value.substring(1, value.length() - 1);
            } else {
                throw new UHCConfigWrongValueTypeException(option, valueType);
            }
        } catch (NumberFormatException e) {
            throw new UHCConfigWrongValueTypeException(option, valueType);
        }
    }
}
