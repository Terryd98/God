package test.land;

import test.gfx.Render;

public class Pixel {

	public static Pixel[] pixels = new Pixel[120];
	public int id;
	public int size = 1;

	public static Pixel ocean = new Ocean(0);
	public static Pixel landMass = new LandMass(1);
	
	public Pixel(int id) {
		this.id = id;
		pixels[id] = this;
	}
	
	public void render(Render render, int x, int y) {
		
	}


}
