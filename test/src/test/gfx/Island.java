package test.gfx;

import java.util.ArrayList;
import java.util.Random;
import test.land.LandMass1;
import test.land.Pixel;

public class Island {

    public ArrayList<LandMass1> landMass = new ArrayList<LandMass1>();
    public int x, y;
    private Level level;
    private Random random = new Random();
    private int time = 0;
    private int tickCount = 0;
    private boolean growing = true;
    public int population = 1;
    private int growthRate = 0;

    public Island(int x, int y, int growthRate, Level level) {
	this.level = level;
	this.x = x;
	this.y = y;
	level.setPixel(x + xa, y + ya, Pixel.landMass);
	level.countries++;
	level.worldPopulation++;
	this.growthRate = growthRate;

    }

    private int xa = 0, ya = 0;

    public void tick() {
	//if (tickCount % 60 == 0 && population > 100) {
	 //   growthRate--;
	//}
	if (growthRate <= 1) growthRate = 1;
	population = landMass.size() + 1;
	for (int i = 0; i < landMass.size(); i++) {
	    landMass.get(i).tick();
	}

	if (x < 0 || y < 0 || y > level.height || x > level.width) growing = false;
	tickCount++;
	if (growing && tickCount % 1 == 0) {
	    if (random.nextBoolean() == true) {
		if (random.nextInt(3) == 0) {
		    if (random.nextBoolean() == true) {
			xa++;
		    } else {
			xa--;
		    }
		} else if (random.nextInt(3) == 1) {
		    if (random.nextBoolean() == true) {
			ya++;
		    } else {
			ya--;
		    }
		} else {
		    if (random.nextBoolean() == true) {
			xa++;
		    } else {
			xa--;
		    }
		    if (random.nextBoolean() == true) {
			ya++;
		    } else {
			ya--;
		    }
		}
		if (!level.hasPixel(x + xa, y + ya)) {
		    landMass.add(new LandMass1(x + xa, y + ya));
		    level.worldPopulation++;
		    // level.setPixel(x + xa, y + ya, Pixel.landMass);
		}
	    }
	    time++;

	}
    }

    public void render(Render render) {
	for (int i = 0; i < landMass.size(); i++) {
	    landMass.get(i).render(render);
	}
	
	//render.renderLandMass(x, y, 0xff00ff);
    }

}
