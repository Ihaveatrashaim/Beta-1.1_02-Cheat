package net.minecraft.cheat.impl.movement;

import org.lwjgl.input.Keyboard;

import net.minecraft.cheat.Category;
import net.minecraft.cheat.Cheat;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Packet11PlayerPosition;

public class NoClip extends Cheat {

	
	public NoClip() {
		super("NoClip", 0, Category.Movement);
	}
	
	@Override
	public void onDisabled() {
	}
	
	public double direction() {
        EntityPlayer player = mc.thePlayer;
        double rotationYaw = player.rotationYaw;
        if (player.moveForward < 0f) rotationYaw += 180f;
        float forward = 1f;
        if (player.moveForward < 0f) forward = -0.5f; 
        if (player.moveForward > 0f) forward = 0.5f;
        if (player.moveStrafing > 0f) rotationYaw -= 90f * forward;
        if (player.moveStrafing < 0f) rotationYaw += 90f * forward;
        return Math.toRadians(rotationYaw);
    }
	
	@Override
	public void onTick() {
		if(mc.thePlayer == null || mc.theWorld == null) return;
		
		 mc.thePlayer.motionY = 0;
	     mc.thePlayer.motionZ *= 0;
	     mc.thePlayer.motionX *= 0;
		
	     if(Keyboard.isKeyDown(mc.gameSettings.keyBindSneak.keyCode)) {
	        	mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY - 0.6, mc.thePlayer.posZ);
			}
			if(Keyboard.isKeyDown(mc.gameSettings.keyBindJump.keyCode)) {
	        	mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.6, mc.thePlayer.posZ);
			}
	     
		if(mc.thePlayer.movementInput.moveForward == 0 && mc.thePlayer.movementInput.moveStrafe == 0) return;
				
		
		mc.thePlayer.isCollidedHorizontally = false;
		
		double yaw = direction();
        double x = -Math.sin(yaw) * 0.12;
        double z = Math.cos(yaw) * 0.12;

        
        mc.thePlayer.setPosition(mc.thePlayer.posX + x, mc.thePlayer.posY, mc.thePlayer.posZ + z);
        
        
	}
	
	@Override
	public void onKey(int key) {
	}
}
