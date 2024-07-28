package net.worldmc.combatTag.managers;

import net.worldmc.combatTag.CombatTag;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.stream.Collectors;

public class ConfigManager {
    private final int tagDuration;
    private final String tagMessage;
    private final String untagMessage;
    private final String blockedMessage;
    private final List<String> blockedCommands;

    public ConfigManager(FileConfiguration config) {
        this.tagDuration = config.getInt("tag-duration", 30) * 20; // Convert to ticks
        this.tagMessage = config.getString("tag-message", "<red>You have been combat tagged!");
        this.untagMessage = config.getString("untag-message", "You are no longer combat tagged.");
        this.blockedMessage = config.getString("blocked-message", "<red>You cannot use this command while combat tagged!");
        this.blockedCommands = config.getStringList("blocked-commands");
    }

    public int getTagDuration() {
        return tagDuration;
    }

    public String getTagMessage() {
        return tagMessage;
    }

    public String getUntagMessage() {
        return untagMessage;
    }

    public String getBlockedMessage() {
        return blockedMessage;
    }

    public List<String> getBlockedCommands() {
        return blockedCommands;
    }
}