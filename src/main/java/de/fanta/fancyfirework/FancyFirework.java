package de.fanta.fancyfirework;

import de.fanta.fancyfirework.commands.CommandRegistration;
import de.fanta.fancyfirework.fireworks.FireWorkRegistration;
import de.fanta.fancyfirework.listners.EventRegistration;
import de.fanta.fancyfirework.utils.ChatUtil;
import de.fanta.fancyfirework.utils.WorldGuardHelper;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.kitteh.vanish.VanishPlugin;

import java.util.List;
import java.util.logging.Logger;

public final class FancyFirework extends JavaPlugin {

    public static final String PREFIX = ChatUtil.BLUE + "[" + ChatUtil.GREEN + "FancyFirework" + ChatUtil.BLUE + "]";
    public static final String ADMIN_PERMISSION = "fancyfirework.admin";
    public static final String MOD_PERMISSION = "fancyfirework.mod";
    public static Logger LOGGER;

    private FireWorkWorks fireWorkWorks;
    private FireWorksRegistry registry;
    private VanishPlugin vanish;
    private WorldGuardHelper worldGuardHelper;

    private long time;
    private int taskId;

    private static FancyFirework plugin;

    @Override
    public void onEnable() {
        LOGGER = getLogger();
        plugin = this;

        if (plugin.getServer().getPluginManager().getPlugin("VanishNoPacket") != null) {
            vanish = (VanishPlugin) plugin.getServer().getPluginManager().getPlugin("VanishNoPacket");
        }

        if (plugin.getServer().getPluginManager().getPlugin("WorldGuard") != null) {
            worldGuardHelper = new WorldGuardHelper(getServer().getPluginManager().getPlugin("WorldGuard"));
        }

        new bStats(this).registerbStats();

        fireWorkWorks = new FireWorkWorks();
        this.registry = new FireWorksRegistry(this);

        new CommandRegistration(this).registerCommands();
        new EventRegistration(this).registerEvents();
        new FireWorkRegistration(this).registerFirework();

        saveDefaultConfig();
        reloadConfig();

        this.taskId = -1;
        this.time = 0;
        this.restartTask(1);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static FancyFirework getPlugin() {
        return plugin;
    }

    public FireWorkWorks getFireWorkWorks() {
        return fireWorkWorks;
    }

    public FireWorksRegistry getRegistry() {
        return registry;
    }

    public boolean isVanish(Player p) {
        if (vanish != null) {
            return vanish.getManager().isVanished(p);
        } else {
            return false;
        }
    }

    public void restartTask(long l) {
        if (this.taskId != -1) {
            this.getServer().getScheduler().cancelTask(this.taskId);
        }
        this.taskId = this.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> FancyFirework.this.time++, 0L, l);
    }

    public long getTime() {
        return this.time;
    }

    public boolean canBuild(Player player, Location loc) {
        if (worldGuardHelper == null) {
            return true;
        } else {
            return worldGuardHelper.canBuild(player, loc);
        }
    }
}
