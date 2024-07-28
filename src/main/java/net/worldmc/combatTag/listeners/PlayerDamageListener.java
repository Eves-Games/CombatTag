package net.worldmc.combatTag.listeners;

import net.worldmc.combatTag.CombatTag;
import net.worldmc.combatTag.managers.TagManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamageListener implements Listener {
    private final TagManager tagManager;

    public PlayerDamageListener(CombatTag plugin) {
        this.tagManager = plugin.getTagManager();
    }

    @EventHandler
    public void onPlayerDeath(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player victim) || !(event.getDamager() instanceof Player attacker)) {
            return;
        }

        tagManager.assignTag(victim);
        tagManager.assignTag(attacker);
    }
}
