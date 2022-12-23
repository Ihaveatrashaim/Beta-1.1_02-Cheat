package net.minecraft.cheat;

public class Client {

	public static Client i;
	public CheatList cheatList;
	
	public Client() {
		i = this;
	}
	
	public void init() {
		cheatList = new CheatList();
	}
}
