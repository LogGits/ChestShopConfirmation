package me.droreo002.chestshopconfirmation;

import lombok.Getter;
import me.droreo002.chestshopconfirmation.commands.CShopConfirmationCommand;
import me.droreo002.chestshopconfirmation.config.ConfigManager;
import me.droreo002.chestshopconfirmation.debug.Debug;
import me.droreo002.chestshopconfirmation.listener.CoreListener;
import me.droreo002.oreocore.OreoCore;
import me.droreo002.oreocore.debugging.Debugger;
import me.droreo002.oreocore.utils.bridge.ServerUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import static java.lang.System.*;

public class ChestShopConfirmation extends JavaPlugin {

    @Getter
    private static ChestShopConfirmation instance;
    @Getter
    private ConfigManager configManager;
    @Getter
    private Debugger debug;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        configManager = new ConfigManager(this);
        debug = new Debug(this);

        debug.log("&8&m+----------------------------------------------------+", Level.INFO, false, true);
        out.println(" ");
        debug.log("                 &aChestShopConfirmation", Level.INFO, false, true);
        debug.log("&7> &fEnabling core features...", Level.INFO, false, true);
        debug.log("&7> &fSetting up config.yml....", Level.INFO, false, true);
        debug.log("&7> &fRegistering commands...", Level.INFO, false, true);
        debug.log("&7> &fFinish!", Level.INFO, false, true);
        out.println(" ");
        debug.log("&8&m+----------------------------------------------------+", Level.INFO, false, true);

        new CShopConfirmationCommand(this);
        ServerUtils.registerListener(this, new CoreListener(this));
        OreoCore.getInstance().dependPlugin(this, false);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        debug.log("Plugin has been disabled!", Level.INFO, true, true);
    }
}
