package desktopviewer;

import java.awt.*;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import Console.*;

/**
 *
 * @author Jun
 */
public class StartServer
{	
    private ServerSocket _serverSocket = null;
    private DataOutputStream _outPutStream = null;
    private IConsole _console;
    public String width="";
    public String height="";

    StartServer(int port)
    {
        _console = new Console();
        
        Robot robot = null;
        Rectangle rectangle = null;
        try
        {
            _console.WriteLine("Awaiting Connection from Client");
            _serverSocket = new ServerSocket(port);

            GraphicsEnvironment graphicEnviroment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice graphicDevice = graphicEnviroment.getDefaultScreenDevice();

            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            width = "" + dimension.getWidth();
            height = "" + dimension.getHeight();
            rectangle = new Rectangle(dimension);
            robot = new Robot(graphicDevice);

            while(true)
            {
                Socket socket =_serverSocket.accept();
                _outPutStream = new DataOutputStream(socket.getOutputStream());
                _outPutStream.writeUTF("valid");
                _outPutStream.writeUTF(width);
                _outPutStream.writeUTF(height);
                new SendScreen(socket,robot,rectangle);
                new ReceiveEvents(socket,robot);
            }
        }
        catch (Exception ex)
        {
             _console.WriteLine(ex.getMessage());
        }
    }
}
