package test.land;

import test.gfx.Render;

public class Ocean extends Pixel {

	public Ocean(int id) {
		super(id);
	}

	public void tick() {

	}

	public void render(Render render, int x, int y) {
		render.renderLandMass(x, y,  0x4291ED);
	}
}
