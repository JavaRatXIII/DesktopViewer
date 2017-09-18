package desktopviewer;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import javax.imageio.ImageIO;
import Console.*;

class SendClientScreen extends Thread
{
    Socket _serverSocket = null;
    Robot _screenCapturer = null;
    Rectangle _screen = null;
    OutputStream _outputStream = null;

    public SendClientScreen(Socket socket,Robot robot,Rectangle rectangle) 
    {
        _serverSocket = socket;
        _screenCapturer = robot;
        _screen = rectangle;
        start();
    }

    public void run()
    {
        try
        {
            _outputStream = _serverSocket.getOutputStream();
        }
        catch(IOException ex)
        {
            new Console().WriteLine(ex.getMessage());
        }

        while(true)
        {
            BufferedImage image=_screenCapturer.createScreenCapture(_screen);

            try
            {
                ImageIO.write(image,"jpeg",_outputStream);
                Thread.sleep(10);
            }
            catch(IOException | InterruptedException ex)
            {
                new Console().WriteLine(ex.getMessage());
                break;
            }
        }
    }
}

