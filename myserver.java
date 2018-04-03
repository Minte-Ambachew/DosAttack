import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Random;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server {
        public static void main(String[] args) throws Exception {
                HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
                server.createContext("/", new Greeter());
                server.setExecutor(null); // Set to default
                server.start();
        }

        static class Greeter implements HttpHandler {
                @Override
                public void handle(HttpExchange t) throws IOException {
                        String response = "Hello, Ajita";
                        Random rand = new Random();
                        for (int i = 0; i < rand.nextInt(20); i++)
                                response += "a";

                        t.sendResponseHeaders(200, response.length());
                        OutputStream os = t.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                }
        }
}