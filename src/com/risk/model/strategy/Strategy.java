package com.risk.model.strategy;

import java.util.ArrayList;

import com.risk.model.map.Country;
import com.risk.model.player.Player;

import javafx.collections.ObservableList;

public interface Strategy {
	void setup(Player p);
	void attack(Player p);
	void reinforce(Player p);
	void fortify(Player p);
}
