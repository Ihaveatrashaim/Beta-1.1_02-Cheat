package net.minecraft.cheat;

import net.minecraft.client.Minecraft;

public class Cheat {
	
	public Minecraft mc = Minecraft.theMinecraft;
	public boolean toggled;
	
	public String name;
	public int key;
	public Category category;
	
	public Cheat(String string, int key, Category c) {
		this.category = c;
		this.name = string;
		this.key = key;
	}

	public void onEnabled() {
		
	}
	
	public void onDisabled() {
		
	}
	
	public void onTick() {
		
	}
	
	public void onRender() {
		
	}
	
	public void onKey(int key) {
		
	}
	
	public void toggle() {
		setToggled(!toggled);
	}

	
	public boolean isToggled() {
		return toggled;
	}

	public void setToggled(boolean toggled) {
		this.toggled = toggled;
		
		if(this.toggled) this.onEnabled();
		else this.onDisabled();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
