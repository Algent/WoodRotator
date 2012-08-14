package eu.algent.WoodRotator;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class WoodRotator extends JavaPlugin implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info(getName() + " has been enabled.");
    }

    public void onDisable() {
        getLogger().info(getName() + " has been disabled.");
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // Check if left click + sneaking
        if (!(event.getAction().equals(Action.LEFT_CLICK_BLOCK) 
                && event.getPlayer().isSneaking())) return;
        // Check if clicked block is wood
        Block block = event.getClickedBlock();
        if (block.getType() != Material.LOG) return;
        // Check player permissions
        if (!event.getPlayer().hasPermission("woodrotator.rotate")) return;
        Boolean barkFace = event.getPlayer().hasPermission("woodrotator.bark");
        // Rotate Wood
        Byte data = block.getData();
        switch (data) {
        // Oak
        case 0: 
            data = 4;
            break;
        case 4: 
            data = 8;
            break;
        case 8: 
            data = 12;
            if(barkFace) break;
        case 12: 
            data = 0;
            break;
        // Pine
        case 1: 
            data = 5;
            break;
        case 5: 
            data = 9;
            break;
        case 9: 
            data = 13;
            if(barkFace) break;
        case 13: 
            data = 1;
            break;
        // Birch
        case 2: 
            data = 6;
            break;
        case 6: 
            data = 10;
            break;
        case 10: 
            data = 14;
            if(barkFace) break;
        case 14: 
            data = 2;
            break;
        // Jungle
        case 3: 
            data = 7;
            break;
        case 7: 
            data = 11;
            break;
        case 11: 
            data = 15;
            if(barkFace) break;
        case 15: 
            data = 3;
            break;
        }
        block.setData(data);
    }
}
