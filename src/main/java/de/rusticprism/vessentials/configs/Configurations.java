package de.rusticprism.vessentials.configs;

import de.rusticprism.vessentials.util.NameableStorage;

public class Configurations {
    public final NameableStorage<Configuration> configs;
    public Configurations() {
        configs = new NameableStorage<>(Configuration::getName);
    }
    public void register(Configuration configuration) {
        configs.add(configuration);
    }
    public <C extends Configuration> C getConfig(Class<C> class_) {
        return configs.get(class_);
    }
}
