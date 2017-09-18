package desktopviewer;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;

public class StartServer{
		
	ServerSocket socket = null;
	DataInputStream password = null;
	DataOutputStream verify = null;
	String width="";
	String height="";
			
	StartServer(int port){
		Robot robot = null;
		Rectangle rectangle = null;
		try{
			System.out.println("Awaiting Connection from Client");
			socket=new ServerSocket(port);
			
			GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice gDev = gEnv.getDefaultScreenDevice();
	
			Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
			String width=""+dim.getWidth();
			String height=""+dim.getHeight();
			rectangle=new Rectangle(dim);
			robot=new Robot(gDev);

			drawGUI();

			while(true){
				Socket sc=socket.accept();
				password=new DataInputStream(sc.getInputStream());
				verify=new DataOutputStream(sc.getOutputStream());
				//String username=password.readUTF();
				String pssword=password.readUTF();
				pssword = "";
				if(pssword.equals("")){
					verify.writeUTF("valid");
					verify.writeUTF(width);
					verify.writeUTF(height);
					new SendClientScreen(sc,robot,rectangle);
					new ReceiveEvents(sc,robot);}
				else{
					verify.writeUTF("Invalid");
				}
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}
			
	private void drawGUI(){
	}

}
