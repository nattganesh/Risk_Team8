package com.risk.model.strategy;

import com.risk.model.map.Country;
import com.risk.model.player.Player;

public interface Strategy {
	boolean attack(Player p,boolean status);
	void reinforce(Player p);
	void fortify(Player p);
}
