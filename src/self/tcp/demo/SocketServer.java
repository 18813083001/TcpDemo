package self.tcp.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        boolean isStopped = false;

        System.out.println("server start!");
        Socket clientSocket = serverSocket.accept();
        //do something with clientSocket
        InputStream inputStream = clientSocket.getInputStream();
        OutputStream outputStream = clientSocket.getOutputStream();

        while (!isStopped){
            byte[] bytes =  new byte[1024];
            int len = inputStream.read(bytes);//客户端的outputStream调用close时，会读取到-1，如果是客户端的socket调用close时，会出现异常
            if (len != -1){
                String str = new String(bytes,0,len);
                System.out.println("receive from client:"+str);
            }else {
                inputStream.close();
                outputStream.close();
                isStopped = true;
            }
        }
        clientSocket.close();
        serverSocket.close();
        System.out.println("client quit!");
    }
}
