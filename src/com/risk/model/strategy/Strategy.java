package com.risk.model.strategy;

import com.risk.model.map.Country;
import com.risk.model.player.Player;

public interface Strategy {
	void setup(Player p);
	void attack(Player p);
	void reinforce(Player p);
	void fortify(Player p);
}
