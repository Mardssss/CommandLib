package com.github.mardssss.commandlib;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * BaseCommand serves as a base class for creating commands with subcommands
 * in a Bukkit/Spigot plugin. It implements CommandExecutor and TabCompleter
 * interfaces to handle command execution and tab completion.
 */
public abstract class BaseCommand implements CommandExecutor, TabCompleter {
    private final Map<String, SubCommand> subCommands = new HashMap<>();
    private final String commandName;
    private String usageMessage; // Field to hold the usage message

    /**
     * Constructs a new BaseCommand instance with the specified command name,
     * and a default usage message
     *
     * @param commandName The name of the command.
     */
    public BaseCommand(String commandName) {
        this.commandName = commandName;
        this.usageMessage = setDefaultUsageMessage(); // Set default usage message
    }

    /**
     * Registers a subcommand with the specified name.
     * <p>Make sure you register {@link CommandLib#registerCommand(BaseCommand)} begore trying to register the coomand</>
     *
     * @param name      The name of the subcommand.
     * @param subCommand The SubCommand instance to register.
     */
    protected void registerSubCommand(String name, SubCommand subCommand) {
        subCommands.put(name.toLowerCase(), subCommand);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase(commandName)) {
            if (args.length == 0) {
                sender.sendMessage(getUsageMessage());
                return true;
            }

            String subCommandName = args[0].toLowerCase();
            SubCommand subCommand = subCommands.get(subCommandName);
            if (subCommand != null) {
                return subCommand.executeCommand(sender, cmd, label, args);
            } else {
                sender.sendMessage(getUsageMessage());
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase(commandName)) {
            if (args.length == 1) {
                return subCommands.keySet().stream()
                        .filter(subCommand -> subCommand.startsWith(args[0].toLowerCase()))
                        .collect(Collectors.toList());
            } else if (args.length > 1) {
                String subCommandName = args[0].toLowerCase();
                SubCommand subCommand = subCommands.get(subCommandName);
                if (subCommand != null) {
                    return subCommand.onTabComplete(sender, cmd, label, args);
                }
            }
        }
        return null;
    }

    /**
     * Returns the current {@link #usageMessage Usage Message} for the command.
     * Developers can customize this message as needed.
     *
     * @return The {@link #usageMessage Usage Message} for the command.
     */
    protected String getUsageMessage() {
        return usageMessage;
    }

    /**
     * Allows developers to set a custom {@link #usageMessage Usage Message}.
     *
     * @param usageMessage The custom usage message to set.
     */
    public void setUsageMessage(String usageMessage) {
        this.usageMessage = usageMessage;
    }

    /**
     * Sets the default usage message for the command.
     * This method can be overridden by subclasses to provide a custom default message.
     *
     * @return The default {@link #usageMessage Usage Message} for the command.
     */
    protected String setDefaultUsageMessage() {
        StringBuilder defaultUsageMessage = new StringBuilder("Available Commands:\n");
        for (Map.Entry<String, SubCommand> entry : subCommands.entrySet()) {
            defaultUsageMessage.append("- ").append(commandName).append(" ").append(entry.getKey())
                    .append(": ").append(entry.getValue().getDescription()).append("\n");
        }
        return defaultUsageMessage.toString();
    }

    /**
     * Returns the name of the command.
     *
     * @return The {@link #commandName Command Name}
     */
    public String getCommandName() {
        return this.commandName;
    }
}
