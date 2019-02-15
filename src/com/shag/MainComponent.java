
package com.shag;
import com.shag.game.Game;
import com.shag.game.window.RenderUtil;
import com.shag.game.window.Window;
import com.shag.networking.client.Client;
import com.shag.serialization.SerializationTest;
import com.shag.time.Time;
import com.shag.untils.UniqueIdentifier;

public class MainComponent implements LoginWindowCallBack {
	
	public static final int WIDTH=800;
	public static final int HEIGHT=600;
	public static final String TITLE="Fanuga";
	public static final double FRAME_CAP=5000.0;
	
	private boolean isRunning;
	
	public Game game;
	
	Client client;
	
	public MainComponent() {
//		RenderUtil.initGraphics();
//		
//		isRunning=false;
//		game=new Game();
//		start();
		
		
//		
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

	@Override
	public void OnLoginBtnClick(String name, String address, int port) {
//		System.out.println("lofin click");
//		Window.creatreWindow(WIDTH, HEIGHT, TITLE);
//		game=new Game();
		client=new Client(name, address, port,this);
//		RenderUtil.initGraphics();
//		isRunning=false;
//		start();
		
		
	}
	
	public void OnSuccessfullConnection(String name, int ID, boolean isOwn) {
		Window.creatreWindow(WIDTH, HEIGHT, TITLE);
		RenderUtil.initGraphics();
		game=new Game();
		game.CreatePlayer(name, ID, isOwn);
		isRunning=false;
		start();
	}
	
	
	public static void main(String[] args) {
//		Window.creatreWindow(WIDTH, HEIGHT, TITLE);
		LoginWindow frame = new LoginWindow();
		frame.setVisible(true);
		MainComponent game=new MainComponent();
		frame.register(game);
//		game.OnLoginBtnClick();

	}
	
//	public static void main(String[] args) {
//		String name="shag";
//		String string=name+"-"+UniqueIdentifier.getIdentifier();
//		System.out.println("string "+string);
//		String s[]=string.split("-");
//		int id=Integer.parseInt(s[1]);
//		System.out.println(s.length+" , "+id);;
//	}
	
	public static void printHex(int value) {
		System.out.printf("%x\n",value);
	}

	public static void printBin(int value) {
		System.out.println(Integer.toBinaryString(value));
	}
	
	
}
