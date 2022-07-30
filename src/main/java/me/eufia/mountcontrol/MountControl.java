package me.eufia.mountcontrol;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import me.eufia.mountcontrol.commands.MountControlCommands;
import me.eufia.mountcontrol.events.EventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

@Mod(modid = MountControl.MODID, version = MountControl.VERSION, acceptableRemoteVersions = "*")
public class MountControl
{
    public static final String MODID = "mountcontrol";
    public static final String VERSION = "1.0";

    public static String mountDisabledMessage;
    public static boolean areMountsDisabled;

    public static Configuration configFile;

    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        System.out.println("MountControl Initialized");
    }

    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        mountDisabledMessage = config.getString("mountDisabledMessage", "String", "Usage of mounts is disabled right now!", "Message that shows to player when they try to use a mount while it's disabled.");
        areMountsDisabled = config.getBoolean("areMountsDisabled", "boolean", false, "Change to true if you want to disallow the usage of mounts, and false if not.");

        config.save();
        configFile = config;
    }

    @Mod.EventHandler
    public void onServerLoad(final FMLServerStartingEvent event) {
        event.registerServerCommand(new MountControlCommands());
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }
}
