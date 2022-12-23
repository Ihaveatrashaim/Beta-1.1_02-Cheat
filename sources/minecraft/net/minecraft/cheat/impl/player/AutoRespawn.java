package net.minecraft.cheat.impl.player;

import org.lwjgl.input.Keyboard;

import net.minecraft.cheat.Category;
import net.minecraft.cheat.Cheat;
import net.minecraft.cheat.Client;
import net.minecraft.cheat.impl.exploit.GodMod;

public class AutoRespawn extends Cheat {
	
	public AutoRespawn() {
		super("AutoRespawn", 0, Category.Player);
	}
	
	@Override
	public void onDisabled() {
		
	}

	@Override
	public void onEnabled() {
		if(mc.thePlayer == null || mc.theWorld == null) return;
		
	}
	
	@Override
	public void onTick() {
		if(mc.theWorld == null) return;

		if(mc.thePlayer.health <= 0 && !Client.i.cheatList.getCheat(GodMod.class).isToggled()) {
			mc.thePlayer.respawnPlayer();
		}
		
	}
	
	@Override
	public void onKey(int key) {
		
	}
}
