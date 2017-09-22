package Interfaces;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Jun
 */
public interface ISocketFactory 
{
    public ServerSocket getServerSocket() throws IOException;
}
