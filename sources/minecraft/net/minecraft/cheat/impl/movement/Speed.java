package net.minecraft.cheat.impl.movement;

import org.lwjgl.input.Keyboard;

import net.minecraft.cheat.Category;
import net.minecraft.cheat.Cheat;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Packet11PlayerPosition;

public class Speed extends Cheat {

	public float speed = 1;
	
	public Speed() {
		super("Speed", 0, Category.Movement);
	}
	
	@Override
	public void onDisabled() {
		mc.timer.timerSpeed = 1;
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
						
		if(mc.thePlayer.movementInput.moveForward != 0 || mc.thePlayer.movementInput.moveStrafe != 0) {
			if(mc.thePlayer.onGround) {
				mc.thePlayer.motionY += 0.5;
				mc.thePlayer.motionY -= 0.5;
				
				mc.timer.timerSpeed = 1.8f;
			}
			
			mc.timer.timerSpeed = 1.3f;
			
			
		}
	}
	
	@Override
	public void onKey(int key) {
	}
}
