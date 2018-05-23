package me.ddevil.lucinho.command.internal;

import me.ddevil.lucinho.command.Command;
import me.ddevil.lucinho.command.CommandArgs;
import me.ddevil.lucinho.command.CommandOwner;
import me.ddevil.lucinho.exception.ArgumentOutOfRangeException;
import me.ddevil.lucinho.exception.InvalidArgumentException;
import org.jetbrains.annotations.NotNull;

public class PluginCommand extends Command {
    public PluginCommand(@NotNull CommandOwner owner) {
        super(owner);
    }

    @Override
    public void execute(@NotNull CommandArgs args) {
        try {
            args.getString(0);
        } catch (InvalidArgumentException | ArgumentOutOfRangeException e) {
            e.printStackTrace();
        }
    }
}
