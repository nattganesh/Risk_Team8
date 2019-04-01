package com.risk.model.strategy;

import com.risk.model.map.Country;
import com.risk.model.player.Player;

public interface Strategy {
	boolean attack(Player p,boolean status);
	boolean reinforce(Player p,boolean status);
	boolean fortify(Player p,boolean status);
}
