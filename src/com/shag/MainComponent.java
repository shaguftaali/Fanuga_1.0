
package com.shag;
import com.shag.game.Game;
import com.shag.game.window.RenderUtil;
import com.shag.game.window.Window;
import com.shag.networking.client.Client;
import com.shag.time.Time;

public class MainComponent {
	
	public static final int WIDTH=800;
	public static final int HEIGHT=600;
	public static final String TITLE="Fanuga";
	public static final double FRAME_CAP=5000.0;
	
	private boolean isRunning;
	
	private Game game;
	
	public MainComponent() {
		RenderUtil.initGraphics();
		isRunning=false;
		game=new Game();
		
	}
	
	public void start() {
		if(isRunning) {
			return;
		}
		run();
	}
	
	public void stop() {
		if(!isRunning) {
			return;
		}
		isRunning=false;
	}
	
	public void run() {
		isRunning=true;
		final double frameTime=1/FRAME_CAP;
		
		int frames=0;
		double frameCounter=0;
		
		double lastTime=Time.getTime();
		double unprocessedTime=0;
		
	
		
		while(isRunning) {
			
			boolean render=false;
			
			double startTime=Time.getTime();
			double passedTime=startTime-lastTime;
			
			lastTime=startTime;
			unprocessedTime+=passedTime;
			frameCounter+=passedTime;
			
			
			while(unprocessedTime>frameTime) {
				render=true;
				
				unprocessedTime-=frameTime;
				
				if(Window.isCloseRequested()) {
					stop();
				}
				update();
				Time.setDelta(frameCounter);
//				if(frameCounter>=Time.SECOND) {
				if(frameCounter>=1.0f) {
//					System.out.println("frame : "+frames);
					frameCounter=0;
					frames=0;
					
				}
			}
			if(render) {
				render();
				frames++;
			}
			else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
		
		cleanUp();
	}
	
	public void update() {
		game.Input();
		game.Update();
	}
	
	
	public void render() {
		RenderUtil.clearScreen();
		game.Render();
		Window.render();
	}
	
	private void cleanUp() {
		Window.dispose();
	}

	public static void main(String[] args) {
//		Window.creatreWindow(WIDTH, HEIGHT, TITLE);
//		MainComponent game=new MainComponent();
//		game.start();
		Client client=new Client("Shag", "localhost", 9999);

	}

}
