package mapUtil;

import java.awt.Color;
import java.awt.Polygon;

import com.risk.map.Country;

public class MAPObject extends Polygon {

	private Color color;
	private Country country;
	private Neighbour neighbour;
	public MAPObject(int[] x, int[] y) {
		super(x, y, 4);
	}
	public void addArmy(Color col) {
		this.color = col;
	}
	public Color getArmy() {
		return this.color;
	}
	public void addCountry() {
	}	
}
