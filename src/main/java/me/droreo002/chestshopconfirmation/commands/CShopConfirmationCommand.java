package me.droreo002.chestshopconfirmation.commands;

import me.droreo002.chestshopconfirmation.ChestShopConfirmation;
import me.droreo002.chestshopconfirmation.commands.arg.DisableShopCommand;
import me.droreo002.chestshopconfirmation.commands.arg.EnableShopCommand;
import me.droreo002.chestshopconfirmation.commands.arg.HelpCommand;
import me.droreo002.chestshopconfirmation.commands.arg.LoadedDataCommand;
import me.droreo002.chestshopconfirmation.commands.arg.ReloadCommand;
import me.droreo002.chestshopconfirmation.commands.arg.ToggleCommand;
import me.droreo002.chestshopconfirmation.commands.arg.ToggleGlobalCommand;
import me.droreo002.chestshopconfirmation.config.PluginConfig;
import me.droreo002.oreocore.commands.CustomCommand;
import me.droreo002.oreocore.commands.CustomCommandManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CShopConfirmationCommand extends CustomCommand {

    private final PluginConfig memory;
    private final List<String> tabCompletion;

    public CShopConfirmationCommand(ChestShopConfirmation plugin) {
        super(plugin, "chestshopconfirmation", "csc");
        this.memory = plugin.getPluginConfig();
        this.tabCompletion = new ArrayList<>();

        tabCompletion.add("reload");
        tabCompletion.add("toggle");
        tabCompletion.add("toggle-global");
        tabCompletion.add("disable-shop");
        tabCompletion.add("enable-shop");
        tabCompletion.add("help");
        tabCompletion.add("loaded-data");

        addArgument(new ReloadCommand(this, memory, plugin));
        addArgument(new ToggleCommand(this, memory, plugin));
        addArgument(new HelpCommand(this, memory));
        addArgument(new ToggleGlobalCommand(this, memory));
        addArgument(new DisableShopCommand(this, memory, plugin));
        addArgument(new EnableShopCommand(this, memory, plugin));
        addArgument(new LoadedDataCommand(this, memory, plugin));

        CustomCommandManager.registerCommand(plugin, this);
        reload();
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        sendMessage(commandSender, "Plugin made by &eDrOreo002 &fwith love!");
        successSound(commandSender);
    }

    @Override
    public void sendMessage(CommandSender sender, String message) {
        super.sendMessage(sender, memory.getPrefix() + message);
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 1) {
            return createReturnList(tabCompletion, args[0]);
        }
        return null;
    }

    public void reload() {
        setErrorSound(memory.getCmdErrorSound());
        setSuccessSound(memory.getCmdSuccessSound());
        setArgumentNotFoundMessage(memory.getMsgInvalidArg());
    }
}
