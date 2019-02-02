package mapUtil;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.util.ArrayList;
import mapUtil.MapConstant.*;

import javax.swing.JPanel;

public class World extends JPanel {
	private ArrayList<MAPObject> mapObjectsArray;

	public World() {
		setBackground(Color.white);
	}

	public ArrayList<MAPObject> returnWorld() {
		return mapObjectsArray;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // blends the edges
				RenderingHints.VALUE_ANTIALIAS_ON);
		if (mapObjectsArray == null) {
			mapObjectsArray = new ArrayList<MAPObject>();
			initWorld();
		}
		for (MAPObject mapObject : mapObjectsArray) {
			g2.setPaint(Color.black);
			g2.setStroke(new BasicStroke(4));
			g2.draw(mapObject);
			g2.setPaint(mapObject.getArmy());
			g2.fill(mapObject);
		}
	}

	public void initWorld() {
		initContinent("NA");
		initContinent("SA");
		initContinent("AF");
		initContinent("ER");
		initContinent("AS");
		initContinent("AL");
	}

	public void initContinent(String continent) {

		if (continent.equals("NA")) {
			init(MapConstant.numbNorthAmerica, MapConstant.NorthAmerica);
		}
		if (continent.equals("SA")) {
			init(MapConstant.numbSouthAmerica, MapConstant.SouthAmerica);
		}
		if (continent.equals("AF")) {
			init(MapConstant.numbAfrica, MapConstant.Africa);
		}
		if (continent.equals("ER")) {
			init(MapConstant.numbEurope, MapConstant.Europe);
		}
		if (continent.equals("AS")) {
			init(MapConstant.numbAsia, MapConstant.Asia);
		}
		if (continent.equals("AL")) {
			init(MapConstant.numbAustralia, MapConstant.Australia);
		}
	}
	public void init (int numb, double[][] coor) {
		int[] xC, yC;
		int x = 0;
		int y = 1;
		int scale = 50;
		for (int i = 0; i < numb; i++) {
			int scaledX = (int)Math.round( coor[i][x] * scale);
			int scaledY = (int)Math.round(coor[i][y] * scale);
			xC = new int[] { scaledX + 0, scaledX + 0, scaledX + 50, scaledX + 50, };
			yC = new int[] { scaledY + 0, scaledY + 50, scaledY + 50, scaledY + 0, };
			MAPObject mapObj = new MAPObject(xC, yC);
			mapObj.addArmy(Color.white);
			mapObjectsArray.add(mapObj);
		}
	}
}
