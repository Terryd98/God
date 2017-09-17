package test.gfx;

import java.util.ArrayList;
import java.util.Random;

import test.input.Mouse;
import test.land.LandMass;
import test.land.Pixel;

public class Level {

	public int width, height;
	private LandMass ls;
	private Random random = new Random();
	public int[] pixelTiles;
	private Island island;
	private Mouse mouse;
	public int countries = 0;
	public int worldPopulation = 0;

	private ArrayList<Island> islands = new ArrayList<Island>();

	public Level(int width, int height, Mouse mouse) {
		this.width = width;
		this.height = height;
		pixelTiles = new int[width * height];
		setOcean(Pixel.ocean);
	//	islands.add(new Island(30, 30, 20, this));
		this.mouse = mouse;
		// landMass.add(new LandMass(random.nextInt(width), random.nextInt(height), 1));
	}

	private int clickTime = 20;

	public void tick() {
		for (int i = 0; i < islands.size(); i++) {
			islands.get(i).tick();
		}

		if (clickTime > 0) clickTime--;

		if (mouse.mouseB == 1 && clickTime == 0 && !hasPixel(mouse.mouseX / 2, mouse.mouseY / 2)) {
			islands.add(new Island(mouse.mouseX / 2, mouse.mouseY / 2, 10, this));
			clickTime = 1;
		}
	}

	public void renderOcean(int xScroll, int yScroll, Render render) {
		render.setOffsets(xScroll, yScroll);
		int x0 = xScroll;
		int x1 = (xScroll + width);
		int y0 = yScroll;
		int y1 = (yScroll + height);

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getPixel(x, y).render(render, x, y);
			}
		}
		for (int i = 0; i < islands.size(); i++) {
			islands.get(i).render(render);

		}
		// render.renderOcean(0, 0, width, height);

	}

	public boolean hasPixel(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return true;

		if (pixelTiles[x + y * width] == 0) return false;
		else return true;
	}

	public void setOcean(Pixel pixel) {
		for (int yy = 0; yy < height; yy++) {
			for (int xx = 0; xx < width; xx++) {
				pixelTiles[xx + yy * width] = pixel.id;
			}
		}
	}

	public Pixel getPixel(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Pixel.ocean;

		if (pixelTiles[x + y * width] == 0) pixelTiles[x + y * width] = Pixel.ocean.id;
		if (pixelTiles[x + y * width] == 1) pixelTiles[x + y * width] = Pixel.landMass.id;
		return Pixel.pixels[pixelTiles[x + y * width]];
	}

	public void setPixel(int x, int y, Pixel pixel) {
		if (x < 0 || y < 0 || x >= width || y >= height) return;

		pixelTiles[x + y * width] = pixel.id;
	}

	public void render(Render render) {

	}
}
