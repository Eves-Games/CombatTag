package net.worldmc.combatTag.listeners;

import net.worldmc.combatTag.CombatTag;
import net.worldmc.combatTag.managers.TagManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    private final TagManager tagManager;

    public PlayerQuitListener(CombatTag plugin) {
        this.tagManager = plugin.getTagManager();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (tagManager.isTagged(player))
            tagManager.removeTag(player);
    }
}
