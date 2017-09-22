/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Interfaces.ISocketFactory;
import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Jun
 */
public class SocketFactory implements ISocketFactory
{
    @Override
    public ServerSocket getServerSocket() throws IOException
    {
        return new ServerSocket(1204);
    }
}
