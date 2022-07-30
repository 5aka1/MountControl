package me.eufia.mountcontrol.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import me.eufia.mountcontrol.MountControl;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.living.LivingEvent;

public class EventHandler {
    @SubscribeEvent
    public void onMount(LivingEvent.LivingUpdateEvent event) {
        if (event.entity instanceof EntityPlayerMP && MountControl.areMountsDisabled) {
            EntityPlayerMP player = (EntityPlayerMP) event.entity;
            if (player.isRiding() && !player.capabilities.isCreativeMode) {
                player.addChatMessage(new ChatComponentText(MountControl.mountDisabledMessage));
                player.mountEntity(null);
            }
        }
    }
}
