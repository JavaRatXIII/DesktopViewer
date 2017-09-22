package desktopviewer;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import Console.*;
import Interfaces.ISocketFactory;

public class StartServer
{	
    private ServerSocket _serverSocket = null;
    private DataInputStream _inPutStream = null;
    private DataOutputStream _outPutStream = null;
    private IConsole _console;
    public String width = "";
    public String height = "";

    StartServer(ISocketFactory socketFactory)
    {
        _console = new Console();
        Robot robot = null;
        Rectangle rectangle = null;

        try
        {
            _console.WriteLine("Awaiting Client Connection");
            _serverSocket = socketFactory.getServerSocket();

            GraphicsEnvironment graphicEnviroment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice graphicDevice = graphicEnviroment.getDefaultScreenDevice();

            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            width = "" + dimension.getWidth();
            height = "" + dimension.getHeight();
            rectangle = new Rectangle(dimension);
            robot = new Robot(graphicDevice);

            while(true)
            {
                Socket sc = _serverSocket.accept();
                _inPutStream = new DataInputStream(sc.getInputStream());
                _outPutStream = new DataOutputStream(sc.getOutputStream());
                _inPutStream.readUTF();
                _outPutStream.writeUTF("valid");
                _outPutStream.writeUTF(width);
                _outPutStream.writeUTF(height);
                new SendClientScreen(sc,robot,rectangle);
                new ReceiveClientEvents(sc,robot);
            }
        }
        catch (Exception ex)
        {
            _console.WriteLine(ex.getMessage());
        }
    }

}
