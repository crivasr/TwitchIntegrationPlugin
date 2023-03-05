package tk.camikase.TwitchIntegration.HTTPS;

import tk.camikase.TwitchIntegration.TwitchIntegrationPlugin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.InetSocketAddress;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.TrustManagerFactory;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import com.sun.net.httpserver.HttpsServer;
import com.sun.net.httpserver.HttpHandler;

public class MyHttpsServer {

    private HttpsServer httpsServer = null;
    private int port;

    public MyHttpsServer(int port) {

        this.port = port;
        initHttpsServer();
    }

    public int getPort() {
        return port;
    }

    public void addContext(String path, HttpHandler handler) {
        httpsServer.createContext(path, handler);
    }

    public void initHttpsServer() {
        try {
            // setup the socket address
            InetSocketAddress address = new InetSocketAddress(port);

            // initialise the HTTPS server
            httpsServer = HttpsServer.create(address, 0);
            SSLContext sslContext = SSLContext.getInstance("TLS");

            // initialise the keystore
            char[] password = "password".toCharArray();
            KeyStore ks = KeyStore.getInstance("JKS");
            FileInputStream fis = new FileInputStream(
                    TwitchIntegrationPlugin.getInstance().getDataFolder() + "/cert.jks");
            ks.load(fis, password);

            // setup the key manager factory
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, password);

            // setup the trust manager factory
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(ks);

            // setup the HTTPS context and parameters
            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            httpsServer.setHttpsConfigurator(new HttpsConfigurator(sslContext) {
                public void configure(HttpsParameters params) {
                    try {
                        // initialise the SSL context
                        SSLContext context = getSSLContext();
                        SSLEngine engine = context.createSSLEngine();
                        params.setNeedClientAuth(false);
                        params.setCipherSuites(engine.getEnabledCipherSuites());
                        params.setProtocols(engine.getEnabledProtocols());

                        // Set the SSL parameters
                        SSLParameters sslParameters = context.getSupportedSSLParameters();
                        params.setSSLParameters(sslParameters);

                    } catch (Exception ex) {
                        System.out.println("Failed to create HTTPS port");
                    }
                }
            });
            httpsServer.setExecutor(null); // creates a default executor
            httpsServer.start();
            ConsoleCommandSender sender = TwitchIntegrationPlugin.getInstance().getServer().getConsoleSender();
            sender.sendMessage(ChatColor.GREEN + "Creating server on port: " + port);
        } catch (FileNotFoundException e) {
            TwitchIntegrationPlugin.getInstance().getLogger().info("Key not found");
        } catch (Exception e) {
            TwitchIntegrationPlugin.getInstance().getLogger().info("Error creating TwitchIntegration server");
        }
    }
}
