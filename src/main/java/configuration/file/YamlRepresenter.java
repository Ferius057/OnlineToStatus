package configuration.file;

import java.util.LinkedHashMap;
import java.util.Map;
import configuration.ConfigurationSection;
import configuration.serialization.ConfigurationSerializable;
import configuration.serialization.ConfigurationSerialization;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.representer.Representer;

public class YamlRepresenter extends Representer {
    public YamlRepresenter() {
        this.multiRepresenters.put(ConfigurationSection.class, new YamlRepresenter.RepresentConfigurationSection());
        this.multiRepresenters.put(ConfigurationSerializable.class, new YamlRepresenter.RepresentConfigurationSerializable());
    }

    private class RepresentConfigurationSection extends RepresentMap {
        private RepresentConfigurationSection() {
            super();
        }

        public Node representData(Object data) {
            return super.representData(((ConfigurationSection)data).getValues(false));
        }
    }

    private class RepresentConfigurationSerializable extends RepresentMap {
        private RepresentConfigurationSerializable() {
            super();
        }

        public Node representData(Object data) {
            ConfigurationSerializable serializable = (ConfigurationSerializable)data;
            Map<String, Object> values = new LinkedHashMap();
            values.put("==", ConfigurationSerialization.getAlias(serializable.getClass()));
            values.putAll(serializable.serialize());
            return super.representData(values);
        }
    }
}
