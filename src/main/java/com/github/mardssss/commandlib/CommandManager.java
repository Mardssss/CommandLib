package com.github.mardssss.commandlib;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * CommandManager is responsible for managing command registration
 * for a Bukkit/Spigot plugin. It allows the registration of commands
 * and their associated executors and tab completers.
 */
public class CommandManager {
    private final JavaPlugin plugin;

    /**
     * Constructs a new CommandManager instance.
     *
     * @param plugin The JavaPlugin instance that this CommandManager will operate with.
     */
    public CommandManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Registers a BaseCommand with the plugin's command system.
     * This method sets the command executor and tab completer for the specified command.
     *
     * @param command The BaseCommand instance to register.
     *                This command must have been defined in the plugin's plugin.yml file.
     */
    public void registerCommand(BaseCommand command) {
        plugin.getCommand(command.getCommandName()).setExecutor(command);
        plugin.getCommand(command.getCommandName()).setTabCompleter(command);
    }
}
