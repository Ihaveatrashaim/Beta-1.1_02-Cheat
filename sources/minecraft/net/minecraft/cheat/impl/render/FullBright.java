package net.minecraft.cheat.impl.render;

import org.lwjgl.input.Keyboard;

import net.minecraft.cheat.Category;
import net.minecraft.cheat.Cheat;

public class FullBright extends Cheat {
	
	public FullBright() {
		super("FullBright", 0, Category.Render);
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
		if(mc.thePlayer == null || mc.theWorld == null) return;
		mc.theWorld.skylightSubtracted = 0;

	}
	
	@Override
	public void onKey(int key) {
		
	}
}
