package self.tcp.demo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 9000);
        OutputStream out = socket.getOutputStream();

        int i = 0;
        while ( i < 20){
            if (i == 19)
                out.write("quit".getBytes());
            else
                out.write("some data".getBytes());
            out.flush();
            Thread.sleep(500);
            System.out.println(i);
            i++;
        }
        out.close();//调用close方法，服务端会受到-1

        socket.close();

    }
}
