package net.minecraft.cheat.impl.render;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.cheat.Category;
import net.minecraft.cheat.Cheat;
import net.minecraft.cheat.Client;
import net.minecraft.src.Gui;

public class TabGUI extends Cheat {

	public int tab = 0;
	public int currentMod = 0;
	public boolean expanded;
	
	public TabGUI() {
		super("TabGUI", 0, Category.Render);
		
		this.setToggled(true);
	}
	
	@Override
	public void onRender() {
		GL11.glPushMatrix();
				
		Gui.drawRect(1, 12, 1 + 60, 12 + ((Category.values().length) * 12) + 2, new Color(255,255,255,30).getRGB());
		
		Gui.drawRect(1, 12 + (tab * 12), 1 + 60, 12 + (tab * 12) + 12, new Color(69, 201, 140, 120).getRGB());
		
		int y = 15;
		for(Category c : Category.values()) {
			mc.fontRenderer.drawStringWithShadow(c.name(), 6, y, -1);
			
			y += 12;
		}
		
		if(expanded) {
			Gui.drawRect(1 + 60, 12, 1 + 60 + 80, 12 + ((Client.i.cheatList.getCheatsByCategory(Category.get(tab)).size()) * 12) + 2, new Color(255,255,255,30).getRGB());
			Gui.drawRect(1 + 60, 12 + (currentMod * 12), 1 + 60 + 80, 12 + (currentMod * 12) + 12, new Color(69, 201, 140, 120).getRGB());
			
			int y1 = 15;
			for(Cheat c : Client.i.cheatList.getCheatsByCategory(Category.get(tab))) {
				mc.fontRenderer.drawStringWithShadow(c.getName(), 1 + 60 + 6, y1, c.isToggled() ? new Color(69, 201, 140).getRGB() : -1);
				
				y1 += 12;
			}
		}
		
		GL11.glPopMatrix();
	}
	
	@Override
	public void onKey(int key) {
		if(key == Keyboard.KEY_DOWN) {
			if(!expanded) {
				if(tab >= 4) tab = 0;
				else tab += 1;
			}else {
				if(currentMod >= (Client.i.cheatList.getCheatsByCategory(Category.get(tab)).size()) - 1) currentMod = 0;
				else currentMod += 1;
			}
		}
		
		if(key == Keyboard.KEY_UP) {
			if(!expanded) {
				if(tab == 0) tab = 4;
				else tab -= 1;
			}else {
				if(currentMod == 0) currentMod = Client.i.cheatList.getCheatsByCategory(Category.get(tab)).size() - 1;
				else currentMod -= 1;
			}
			
		}
		
		if(key == Keyboard.KEY_LEFT) {
			this.expanded = false;
		}
		
		if(key == Keyboard.KEY_RIGHT) {
			if(Client.i.cheatList.getCheatsByCategory(Category.get(tab)).size() == 0) return;
			if(!expanded) {
				currentMod = 0;
				this.expanded = true;
				return;
			}else {
				Client.i.cheatList.getCheatsByCategory(Category.get(tab)).get(currentMod).toggle();
			}
		}
	}
}
