package Interfaces;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Jun
 */
public interface ISocketFactory 
{
    public Socket getClientSocket(String IP) throws IOException;
}
