package self.tcp.demo;

import java.io.IOException;
import java.net.Socket;

public class NioSocketClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        String message = "1234567890ab\r\n";
        System.out.println(message.getBytes().length);
        StringBuffer buffer = new StringBuffer();
//        System.out.println(message);
        for (int i = 0; i < 1; i++)
            buffer.append(message);
//        System.out.println(buffer.toString());
        int n = 1;
        Socket socket = new Socket("localhost", 8023);
        socket.setKeepAlive(true);
        int size = buffer.toString().getBytes().length * n;
//        System.out.println(size);
        String httpResponse = "HTTP/1.1 200 OK\r\n" +
                "Content-Length: " + size + "\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" +
                "" + buffer.toString() + "";

//        System.out.println("==:" + httpResponse.getBytes().length);
        int i = 0;
//        while (true){
        i++;
//        Thread.sleep(1000);

        socket.getOutputStream().write(httpResponse.getBytes());

        for (int j = 0;j < n-1;j++){
            Thread.sleep(500);
            System.out.println("message: "+j);
            socket.getOutputStream().write(message.getBytes());
        }


        socket.getOutputStream().flush();
        System.out.println("发送成功");
        System.out.println();
        byte[] b = new byte[120];
        System.out.println("接收数据为:");
        socket.getInputStream().read(b);
        System.out.println(new String(b,"utf-8"));
        socket.close();

//            Thread.sleep(1500);
//            socket.getInputStream().read(b);
//            System.out.println(new String(b,"utf-8"));
//             System.out.println("\r\n----");

//            Thread.sleep(50);
//            socket.getOutputStream().write(httpResponse.getBytes());
//             b = new byte[50];
//            socket.getInputStream().read(b);
//            System.out.println(new String(b,"utf-8"));
//        b = new byte[500];
//        socket.getInputStream().read(b);
//        socket.getInputStream().close();
//            Thread.sleep(1000);
//            socket.getOutputStream().write(httpResponse.getBytes());
//            socket.close();

//        }

    }
}
