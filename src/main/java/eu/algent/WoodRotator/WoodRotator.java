package eu.algent.WoodRotator;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class WoodRotator extends JavaPlugin implements Listener {
    private Boolean worldguard = false;
    private WorldGuardPlugin wgPlugin;

    public void onEnable() {
        // Check for WorldGuard
        worldguard = getServer().getPluginManager().getPlugin("WorldGuard") != null;
        if (worldguard) wgPlugin = getWorldguard();
        // Init listener
        getServer().getPluginManager().registerEvents(new WoodRotatorListener(this), this);
        getLogger().info(getName() + " has been enabled.");
    }

    public void onDisable() {
        getLogger().info(getName() + " has been disabled.");
    }

    private WorldGuardPlugin getWorldguard() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            worldguard = false;
            return null;
        }
        return (WorldGuardPlugin) plugin;
    }

    public Boolean canRotateWg(Player player, Block block) {
        if (!worldguard) return true;
        if (wgPlugin.canBuild(player, block)) {
            return true;
        }
        return false;
    }
}
