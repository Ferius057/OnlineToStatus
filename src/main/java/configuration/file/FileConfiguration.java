package configuration.file;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;

import configuration.InvalidConfigurationException;
import configuration.MemoryConfiguration;
import org.apache.commons.lang.Validate;

public abstract class FileConfiguration extends MemoryConfiguration {
    public FileConfiguration() { }

    public void save(File file) throws IOException {
        Validate.notNull(file, "File cannot be null");
        Files.createParentDirs(file);
        String data = this.saveToString();
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), Charsets.UTF_8);

        try {
            writer.write(data);
        } finally {
            writer.close();
        }

    }

    public void save(String file) throws IOException {
        Validate.notNull(file, "File cannot be null");
        this.save(new File(file));
    }

    public abstract String saveToString();

    public void load(File file) throws FileNotFoundException, IOException, InvalidConfigurationException {
        Validate.notNull(file, "File cannot be null");
        FileInputStream stream = new FileInputStream(file);
        this.load((Reader)(new InputStreamReader(stream, Charsets.UTF_8)));
    }

    public void load(Reader reader) throws IOException, InvalidConfigurationException {
        BufferedReader input = reader instanceof BufferedReader ? (BufferedReader)reader : new BufferedReader(reader);
        StringBuilder builder = new StringBuilder();

        String line;
        try {
            while((line = input.readLine()) != null) {
                builder.append(line);
                builder.append('\n');
            }
        } finally {
            input.close();
        }

        this.loadFromString(builder.toString());
    }

    public void load(String file) throws FileNotFoundException, IOException, InvalidConfigurationException {
        Validate.notNull(file, "File cannot be null");
        this.load(new File(file));
    }

    public abstract void loadFromString(String var1) throws InvalidConfigurationException;

    protected abstract String buildHeader();

    public FileConfigurationOptions options() {
        if (this.options == null) {
            this.options = new FileConfigurationOptions(this);
        }

        return (FileConfigurationOptions)this.options;
    }
}
