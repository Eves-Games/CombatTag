package net.worldmc.combatTag;

import net.worldmc.combatTag.listeners.PlayerCommandListener;
import net.worldmc.combatTag.listeners.PlayerDamageListener;
import net.worldmc.combatTag.listeners.PlayerDeathListener;
import net.worldmc.combatTag.listeners.PlayerQuitListener;
import net.worldmc.combatTag.managers.ConfigManager;
import net.worldmc.combatTag.managers.TagManager;
import net.worldmc.morpheus.Morpheus;
import net.worldmc.morpheus.api.MorpheusAPI;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CombatTag extends JavaPlugin {
    private ConfigManager configManager;
    private TagManager tagManager;
    private MorpheusAPI morpheusAPI;

    @Override
    public void onEnable() {
        if (getServer().getPluginManager().getPlugin("Morpheus") instanceof Morpheus)
            morpheusAPI = Morpheus.getAPI();

        saveDefaultConfig();
        this.configManager = new ConfigManager(this.getConfig());
        this.tagManager = new TagManager(this);

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerDamageListener(this), this);
        pluginManager.registerEvents(new PlayerCommandListener(this), this);
        pluginManager.registerEvents(new PlayerQuitListener(this), this);
        pluginManager.registerEvents(new PlayerDeathListener(this), this);
    }

    public MorpheusAPI getMorpheusAPI() {
        return morpheusAPI;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public TagManager getTagManager() {
        return tagManager;
    }
}