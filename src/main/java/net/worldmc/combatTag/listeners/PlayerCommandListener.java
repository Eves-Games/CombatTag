package net.worldmc.combatTag.listeners;

import net.worldmc.combatTag.CombatTag;
import net.worldmc.combatTag.managers.TagManager;
import net.worldmc.morpheus.api.MorpheusAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandListener implements Listener {
    private final MorpheusAPI morpheusAPI;
    private final TagManager tagManager;

    public PlayerCommandListener(CombatTag plugin) {
        this.morpheusAPI = plugin.getMorpheusAPI();
        this.tagManager = plugin.getTagManager();
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (tagManager.isTagged(player)) {
            String command = event.getMessage().substring(1).toLowerCase(); // Remove the '/' and convert to lowercase
            if (tagManager.isCommandBlocked(command)) {
                event.setCancelled(true);
                morpheusAPI.sendPlayerActionBar(player, tagManager.getBlockedMessage());
            }
        }
    }
}
