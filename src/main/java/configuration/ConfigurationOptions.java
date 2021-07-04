package configuration;

public class ConfigurationOptions {
    private char pathSeparator = '.';
    private boolean copyDefaults = false;
    private final MemoryConfiguration configuration;

    protected ConfigurationOptions(MemoryConfiguration configuration) {
        this.configuration = configuration;
    }

    public MemoryConfiguration configuration() {
        return this.configuration;
    }

    public char pathSeparator() {
        return this.pathSeparator;
    }

    public ConfigurationOptions pathSeparator(char value) {
        this.pathSeparator = value;
        return this;
    }

    public boolean copyDefaults() {
        return this.copyDefaults;
    }

    public ConfigurationOptions copyDefaults(boolean value) {
        this.copyDefaults = value;
        return this;
    }
}
