package me.eufia.mountcontrol.commands;

import me.eufia.mountcontrol.MountControl;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.config.Configuration;

public class MountControlCommands extends CommandBase {
    @Override
    public String getCommandName() {
        return "mountcontrol";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "/mountcontrol";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args[0] != null && args[0].equalsIgnoreCase("reload")) {
            Configuration config = MountControl.configFile;
            config.load();

            MountControl.mountDisabledMessage = config.getString("mountDisabledMessage", "String", "Usage of mounts is disallowed right now!", "Message that shows to player when they try to use a mount while it's disabled.");
            MountControl.areMountsDisabled = config.getBoolean("areMountsDisabled", "boolean", false, "Change to true if you want to disallow the usage of mounts, and false if not.");

            config.save();

            if (sender instanceof EntityPlayerMP) {
                sender.addChatMessage(new ChatComponentText("Config reloaded!"));
            } else {
                System.out.println("Config reloaded!");
            }

        } else if (args[0] != null && args[0].equalsIgnoreCase("toggle") && sender instanceof EntityPlayerMP) {
            Configuration config = MountControl.configFile;
            config.load();

            if (MountControl.areMountsDisabled) {
                config.getCategory("boolean").get("areMountsDisabled").set(false);
                MountControl.areMountsDisabled = false;
                sender.addChatMessage(new ChatComponentText("Mounts are now allowed."));
            } else {
                config.getCategory("boolean").get("areMountsDisabled").set(true);
                MountControl.areMountsDisabled = true;
                sender.addChatMessage(new ChatComponentText("Mounts are now disallowed."));
            }

            config.save();
        }
    }
}
