package de.rusticprism.vessentials.listener;

import de.rusticprism.vessentials.VEssentials;
import net.luckperms.api.event.EventBus;
import net.luckperms.api.event.cause.CreationCause;
import net.luckperms.api.event.group.GroupCreateEvent;
import net.luckperms.api.node.types.PrefixNode;

public class LuckpermsListener {
    public LuckpermsListener(EventBus bus) {
        bus.subscribe(VEssentials.PLUGIN, GroupCreateEvent.class,e -> {
            if(e.getCause().equals(CreationCause.COMMAND)) {
            }
        });
    }
}
