package desktopviewer;

import java.awt.Robot;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import Console.*;

class ReceiveEvents extends Thread
{
    private Socket _serverSocket= null;
    private Robot _robot = null;

    public ReceiveEvents(Socket socket, Robot robot)
    {
        _serverSocket = socket;
        _robot = robot;
        start(); 
    }

    public void run()
    {
        Scanner scanner = null;
        try 
        {
            scanner = new Scanner(_serverSocket.getInputStream());
            while(true)
            {
                int command = scanner.nextInt();
                switch(command)
                {
                    case-1:
                        _robot.mousePress(scanner.nextInt());
                        break;
                    case-2:
                        _robot.mouseRelease(scanner.nextInt());
                        break;
                    case-3:
                        _robot.keyPress(scanner.nextInt());
                        break;
                    case-4:
                        _robot.keyRelease(scanner.nextInt());
                        break;
                    case-5:
                        _robot.mouseMove(scanner.nextInt(),scanner.nextInt());
                        break;
                }
            }
        }
        catch(IOException ex)
        {
            new Console().WriteLine(ex.getMessage());
        }
    }
				
}




