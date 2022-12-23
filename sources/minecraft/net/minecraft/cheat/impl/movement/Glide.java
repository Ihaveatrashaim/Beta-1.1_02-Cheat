package net.minecraft.cheat.impl.movement;

import org.lwjgl.input.Keyboard;

import net.minecraft.cheat.Category;
import net.minecraft.cheat.Cheat;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class Glide extends Cheat {

	public Glide() {
		super("Glide", 0, Category.Movement);
	}
	
	@Override
	public void onTick() {
		if(mc.thePlayer == null || mc.theWorld == null) return;
		
		if(mc.thePlayer.fallDistance > 1) {
			mc.thePlayer.motionY = -0.102;
		}
	}
	
	@Override
	public void onKey(int key) {
		
	}
}
