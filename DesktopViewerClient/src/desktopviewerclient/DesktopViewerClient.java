/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopviewerclient;

import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Jun
 */
public class DesktopViewerClient {
    static String port = "1204";

    /**
     * @param args the command line arguments
     */
                    public static void main(String args[]){
			String ip = JOptionPane.showInputDialog("Please enter server ip");
			new DesktopViewerClient().initialize(ip, Integer.parseInt(port));
			}

	public void initialize(String ip, int port){
			try{
				
				Socket sc = new Socket(ip,port);
				System.out.println("Connecting to the Server");
				//Authenticate class is responsible for security purposes
				Authenticate frame1= new Authenticate(sc);
	
				frame1.setSize(300,80);
				frame1.setLocation(500,300);
				frame1.setVisible(true);
			} catch (Exception ex){
				ex.printStackTrace();
			}
					           }
		}
    

