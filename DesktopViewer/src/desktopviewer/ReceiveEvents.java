package desktopviewer;

import java.awt.Robot;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
/* Used to recieve server commands then execute them at the client side*/

class ReceiveEvents extends Thread{
			Socket socket= null;
			Robot robot = null;
			boolean continueLoop = true;

			public ReceiveEvents(Socket socket, Robot robot){

			this.socket = socket;
			this.robot = robot;
			start(); //Start the thread and hence calling run method
			}



			public void run(){
			Scanner scanner = null;
			try {
			scanner = new Scanner(socket.getInputStream());
			while(continueLoop){
			//recieve commands and respond accordingly

			int command = scanner.nextInt();
			switch(command){
					case-1:
                                            //System.out.println(command+" mouse press "+ scanner.nextInt());
					robot.mousePress(scanner.nextInt());
					break;
					case-2:
                                            //System.out.println(command+" mouse release "+ scanner.nextInt());
					robot.mouseRelease(scanner.nextInt());
					break;
					case-3:
                                            //System.out.println(command+" key press "+ scanner.nextInt());
					robot.keyPress(scanner.nextInt());
					break;
					case-4:
                                            //System.out.println(command+" mouse release "+ scanner.nextInt());
					robot.keyRelease(scanner.nextInt());
					break;
					case-5:
                                            //System.out.println(command+" mouse move "+ scanner.nextInt());
					robot.mouseMove(scanner.nextInt(),scanner.nextInt());
					break;
					}

					}
			}catch(IOException ex){
					ex.printStackTrace();
					}
				}//end function
				
				}//end class




