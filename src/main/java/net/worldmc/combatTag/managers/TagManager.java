package net.worldmc.combatTag.managers;

import net.worldmc.combatTag.CombatTag;
import net.worldmc.morpheus.api.MorpheusAPI;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TagManager {
    private final CombatTag plugin;
    private final MorpheusAPI morpheusAPI;
    private final ConfigManager configManager;

    private final Map<UUID, BukkitRunnable> taggedPlayers;

    public TagManager(CombatTag plugin) {
        this.plugin = plugin;
        this.morpheusAPI = plugin.getMorpheusAPI();
        this.configManager = plugin.getConfigManager();
        this.taggedPlayers = new HashMap<>();
    }

    public void assignTag(Player player) {
        UUID playerUUID = player.getUniqueId();

        if (taggedPlayers.containsKey(playerUUID)) {
            taggedPlayers.get(playerUUID).cancel();
        } else {
            morpheusAPI.sendPlayerActionBar(player, configManager.getTagMessage());
        }

        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                removeTag(player);
                morpheusAPI.sendPlayerActionBar(player, configManager.getUntagMessage());
            }
        };

        task.runTaskLater(plugin, configManager.getTagDuration());
        taggedPlayers.put(playerUUID, task);
    }

    public void removeTag(Player player) {
        UUID playerUUID = player.getUniqueId();
        BukkitRunnable task = taggedPlayers.remove(playerUUID);
        if (task != null) {
            task.cancel();
        }
    }

    public boolean isTagged(Player player) {
        return taggedPlayers.containsKey(player.getUniqueId());
    }

    public boolean isCommandBlocked(String command) {
        command = command.toLowerCase();
        for (String blockedCmd : configManager.getBlockedCommands()) {
            if (command.startsWith(blockedCmd)) {
                return true;
            }
        }
        return false;
    }

    public String getBlockedMessage() {
        return configManager.getBlockedMessage();
    }
}