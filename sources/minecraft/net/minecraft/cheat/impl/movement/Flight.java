package net.minecraft.cheat.impl.movement;

import org.lwjgl.input.Keyboard;

import net.minecraft.cheat.Category;
import net.minecraft.cheat.Cheat;

public class Flight extends Cheat {

	public Flight() {
		super("Flight", Keyboard.KEY_G, Category.Movement);
	}
	
	@Override
	public void onTick() {
		if(mc.thePlayer == null || mc.theWorld == null) return;
		
		mc.thePlayer.motionY = 0;
		mc.thePlayer.motionY *= 0;

		
		if(Keyboard.isKeyDown(mc.gameSettings.keyBindSneak.keyCode)) {
			mc.thePlayer.addVelocity(0, -0.2, 0);
		}
		if(Keyboard.isKeyDown(mc.gameSettings.keyBindJump.keyCode)) {
			mc.thePlayer.motionY = 0.06;
		}
	}
	
	@Override
	public void onKey(int key) {
	}
}
