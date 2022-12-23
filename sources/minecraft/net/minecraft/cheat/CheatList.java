package net.minecraft.cheat;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.cheat.impl.*;
import net.minecraft.cheat.impl.combat.*;
import net.minecraft.cheat.impl.exploit.*;
import net.minecraft.cheat.impl.movement.*;
import net.minecraft.cheat.impl.player.*;
import net.minecraft.cheat.impl.render.*;

public class CheatList {

	public ArrayList<Cheat> cheats = new ArrayList<Cheat>();
	
	public CheatList() {
		cheats.add(new Flight());
		cheats.add(new GodMod());
		cheats.add(new NoKick());
		cheats.add(new TabGUI());
		cheats.add(new FullBright());
		cheats.add(new Speed());
		cheats.add(new NoClip());
		cheats.add(new AutoRespawn());
		cheats.add(new Velocity());
		cheats.add(new Glide());
		cheats.add(new NoFall());
	}
	
	public Cheat getCheat(Class<?> klass) {
		Cheat cheat = null;
		
		for(Cheat c : cheats) {
			if(c.getClass() == klass) {
				cheat = c;
				break;
			}
		}
		
		return cheat;
	}
	
	public List<Cheat> getCheatsByCategory(Category category) {
		List<Cheat> cheats = new ArrayList<Cheat>();
		
		for(Cheat c : this.cheats) {
			if(c.category == category) {
				cheats.add(c);
			}
		}
		
		return cheats;
		
	}
}
