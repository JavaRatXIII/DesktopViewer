package Utilities;

import Interfaces.ISocketFactory;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Jun
 */
public class SocketFactory implements ISocketFactory
{
    @Override
    public Socket getClientSocket(String IP) throws IOException
    {
        return new Socket(IP,1204);
    }
}
