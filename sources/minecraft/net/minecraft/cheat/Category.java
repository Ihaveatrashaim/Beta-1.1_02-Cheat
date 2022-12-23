package net.minecraft.cheat;

public enum Category {
	
	Combat,Movement,Player,Render,Exploit;

	public static Category get(int i) {
		if(i == 0) return Combat;
		else if(i == 1) return Movement;
		else if(i == 2) return Player;
		else if(i == 3) return Render;
		else return Exploit;
	}
}
