package net.minecraft.cheat.impl.player;

import org.lwjgl.input.Keyboard;

import net.minecraft.cheat.Category;
import net.minecraft.cheat.Cheat;
import net.minecraft.cheat.Client;
import net.minecraft.cheat.impl.exploit.GodMod;
import net.minecraft.src.Packet103;

public class NoFall extends Cheat {
	
	public NoFall() {
		super("NoFall", 0, Category.Player);
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

		
		mc.thePlayer.isJumping = false;
		
		if(mc.thePlayer.onGround) {
		}else {
			if(mc.thePlayer.fallDistance > 2) {
				mc.thePlayer.fallDistance = 0;
				mc.thePlayer.onGround = true;
				mc.thePlayer.motionY -= mc.thePlayer.fallDistance;
			}
		}
	}
	
	@Override
	public void onKey(int key) {
		
	}
}
