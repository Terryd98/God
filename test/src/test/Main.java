package test;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

import test.gfx.Level;
import test.gfx.Render;
import test.input.Mouse;

public class Main extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 400, HEIGHT = 350;
    public static final int SCALE = 2;
    private JFrame frame;
    private Thread thread;
    private String title = "Sverige";
    private Render render;
    private Level level;
    private int xScroll, yScroll;
    private Mouse mouse;
    private boolean running = false;

    public BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    public int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public Main() {
	setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
	render = new Render(WIDTH, HEIGHT, pixels);
	frame = new JFrame();

	mouse = new Mouse();
	addMouseListener(mouse);
	addMouseMotionListener(mouse);

	level = new Level(400, 250, mouse);
    }

    public void start() {
	thread = new Thread(this, "Display");
	running = true;
	thread.start();
    }

    public void stop() {
	running = false;
	try {
	    thread.join();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    public void run() {
	long lastTime = System.nanoTime();
	double delta = 0;
	double nsPerTick = 1000000000.0 / 60.0;
	long timer = System.currentTimeMillis();
	int frames = 0;
	int ticks = 0;
	while (running) {
	    long now = System.nanoTime();
	    delta += (now - lastTime) / nsPerTick;
	    lastTime = now;
	    while (delta >= 1) {
		tick();
		ticks++;
		delta--;
	    }
	    render();
	    frames++;

	    if (System.currentTimeMillis() - timer >= 1000) {
		System.out.println("ticks: " + ticks + "  |  FPS: " + frames);
		frame.setTitle("God      [ " + "ticks: " + ticks + "  |  FPS: " + frames + " ]");
		ticks = 0;
		frames = 0;
		timer += 1000;
	    }
	}
    }

    public void tick() {
	level.tick();
    }

    public void render() {
	BufferStrategy bs = getBufferStrategy();
	if (bs == null) {
	    createBufferStrategy(3);
	    return;
	}
	Graphics g = bs.getDrawGraphics();
	render.clear();

	xScroll = 0;// (WIDTH / 2 - level.width / 2);
	yScroll = 0;// (HEIGHT / 2 - level.height / 2);
	level.renderOcean(xScroll, yScroll, render);

	g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	g.setColor(Color.getHSBColor(100, 110, 150));
	g.setColor(Color.DARK_GRAY);
	//g.fillRect(150 * 3, 0, getWidth(), getHeight());
///	g.fillRect(0, 150 * 3, getWidth() - 150, getHeight());

	/*
	 * g.setColor(Color.BLACK); g.setFont((new Font("Verdana", Font.BOLD, 20)));
	 * g.drawString("God", (150 / 2) * 3, 157 * 3); g.setColor(Color.WHITE);
	 * g.drawString("God", (150 / 2) * 3, 158 * 3);
	 */

	g.setColor(Color.WHITE);
	g.setFont((new Font("Verdana", Font.BOLD, 20)));
	g.drawString("Countries: " + Integer.toString(level.countries), 10, HEIGHT*2-60);
	g.drawString("World Population: " + Integer.toString(level.worldPopulation), 10, HEIGHT*2-35);

	g.dispose();
	bs.show();
    }

    public static void main(String args[]) {
	Main main = new Main();
	main.frame.setResizable(false);
	main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	main.frame.add(main);
	main.frame.pack();
	main.frame.setLocationRelativeTo(null);
	main.frame.setVisible(true);
	main.start();
    }
}