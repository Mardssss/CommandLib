package com.github.mardssss.commandlib;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * CommandLib is a utility class for managing command registration
 * in Bukkit/Spigot plugins. It provides methods to initialize the
 * command manager and register commands.
 */
public final class CommandLib {

    private static CommandManager commandManager;

    /**
     * Initializes the CommandLib with the given JavaPlugin instance.
     * This method should be called in the onEnable method of the main plugin class.
     *
     * @param plugin The JavaPlugin instance that will be used for command management.
     */
    public static void initialize(JavaPlugin plugin) {
        commandManager = new CommandManager(plugin);
    }

    /**
     * Retrieves the current CommandManager instance.
     *
     * @return The CommandManager instance used by CommandLib.
     */
    public static CommandManager getCommandManager() {
        return commandManager;
    }

    /**
     * Registers a BaseCommand with the command manager.
     * This method must be called after initializing CommandLib.
     *
     * @param command The BaseCommand instance to register.
     *                This command must have been defined in the plugin's plugin.yml file.
     * @throws IllegalStateException if CommandLib has not been initialized.
     */
    public static void registerCommand(BaseCommand command) {
        if (commandManager == null) {
            throw new IllegalStateException("CommandLib is not initialized. Call CommandLib.initialize(plugin) in your plugin's onEnable method.");
        }
        commandManager.registerCommand(command);
    }
}
