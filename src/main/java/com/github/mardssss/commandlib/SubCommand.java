package com.github.mardssss.commandlib;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface SubCommand {
    /**
     * Executes the sub-command.
     *
     * @param sender the command sender who executed the command
     * @param command the command that was executed
     * @param label the label of the command (e.g. the alias used to execute the command)
     * @param args the arguments passed to the command, starting from the first argument (args[1])
     * @return true if the command was executed successfully, false otherwise
     */
    boolean executeCommand(CommandSender sender, Command command, String label, String[] args);

    /**
     * Provides a list of possible tab completions for the sub-command.
     *
     * @param sender the command sender who is requesting tab completion
     * @param cmd the command that is being completed
     * @param alias the alias of the command that is being completed
     * @param args the arguments that have been entered so far, with the last argument being the one that needs to be completed
     *              Note: starts from args length == 2, first completion
     * @return a list of possible tab completions for the last argument
     */
    List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args);

    /**
     *
     * @return a short description of the sub-command
     */
    String getDescription();
}
