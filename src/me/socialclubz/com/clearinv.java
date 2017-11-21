package me.socialclubz.com;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.MainLogger;
import java.io.File;
import java.util.LinkedHashMap;

public class clearinv
        extends PluginBase
        implements Listener
{
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(this, this);
        getDataFolder().mkdir();

        Config config = new Config(new File(
                getDataFolder(), "config.yml"), 2, new LinkedHashMap() {});
        config.save();

        getServer().getLogger().info("-----------------------");
        getServer().getLogger().info("|      Enabled!       |");
        getServer().getLogger().info("|   ClearInventory    |");
        getServer().getLogger().info("|         by          |");
        getServer().getLogger().info("|     SocialClubz     |");
        getServer().getLogger().info("-----------------------");
    }

    public void onDisable()
    {
        getServer().getLogger().info("-----------------------");
        getServer().getLogger().info("|      Disabled!      |");
        getServer().getLogger().info("|   ClearInventory    |");
        getServer().getLogger().info("|         by          |");
        getServer().getLogger().info("|     SocialClubz     |");
        getServer().getLogger().info("-----------------------");
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = null;
        if ((sender instanceof Player)) {
            player = (Player)sender;
        }
        if (command.getName().equalsIgnoreCase("ci"))
        {
            if (player == null)
            {
                sender.sendMessage("You have to be a Player");
                return true;
            }
            if ((player != null) &&
                    (player.hasPermission("social.ci")))
            {
                player.isOp();
                PlayerInventory inv = player.getInventory();
                inv.clearAll();
                sender.sendMessage("Your Inventory has been cleared");
            }
        }
        return false;
    }
}
