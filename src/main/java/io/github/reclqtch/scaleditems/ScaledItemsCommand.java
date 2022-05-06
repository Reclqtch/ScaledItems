package io.github.reclqtch.scaleditems;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class ScaledItemsCommand implements ICommand {

    @Override
    public String getCommandName() {
        return "scaleditems";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/scaleditems";
    }

    @Override
    public List<String> getCommandAliases() {
        return Collections.emptyList();
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        ScaledItems.showGui = true;
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(@Nonnull ICommand c) {
        return 0;
    }
}
