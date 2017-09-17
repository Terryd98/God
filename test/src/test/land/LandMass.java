package test.land;

import test.gfx.Render;

public class LandMass extends Pixel {

	public LandMass(int id) {
		super(id);
	}

	public void tick() {

	}

	public void render(Render render, int x, int y) {
		render.renderLandMass(x, y,  0x6aff07);
	}
}
