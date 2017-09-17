package test.gfx;

public class Render {

	public int width, height;
	public int[] pixels;
	private int xOffset, yOffset;

	public Render(int width, int height, int[] pixels) {
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void renderOcean(int xo, int yo, int xlim, int ylim) {
		xo += xOffset;
		yo += yOffset;
		for (int y = yo; y < yo + ylim; y++) {
			for (int x = xo; x < xo + xlim; x++) {
				if (x < 0 || y < 0 || x >= width || y >= height) break;
				if (x < 0) x = 0;
				pixels[x + y * width] = 0x42BFED;
			}
		}
	}

	public void renderLandMass(int xp, int yp, int col) {
		xp += xOffset;
		yp += yOffset;
		pixels[xp + yp * width] = col;
	}

	public void setOffsets(int xScroll, int yScroll) {
		this.xOffset = xScroll;
		this.yOffset = yScroll;
	}

}
