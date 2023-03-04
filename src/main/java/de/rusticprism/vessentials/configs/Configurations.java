package de.rusticprism.vessentials.configs;

import de.rusticprism.vessentials.util.NameableStorage;

public class Configurations {
    public static final NameableStorage<Config> configs = new NameableStorage<>(Config::getName);
    public static void register(Config configuration) {
        configs.add(configuration);
    }
    public static void unregister(Config configuration) {
        configs.remove(configuration);
    }
    public static <C extends Config> C getConfig(Class<C> class_) {
        return configs.get(class_);
    }
}
