package test.land;

import test.gfx.Render;

public class LandMass1 {

	public int x, y;
	public int size;

	public LandMass1(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		
	}

	public void render(Render render) {
		render.renderLandMass(x, y, 0x7FDB34);
	}

}
