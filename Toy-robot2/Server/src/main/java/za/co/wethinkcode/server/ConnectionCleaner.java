package za.co.wethinkcode.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class ConnectionCleaner implements Runnable {
    ArrayList<Socket> connections;
    boolean starting = true;

    public boolean adding = false;
    public boolean iterating = false;

    ServerSocket ss;

    /**
     * A connection cleaner will keep a list of all connections to the current
     * server handler and close the connection handler once all active connections
     * close.
     * 
     * @param ss: The server socket to manage
     */
    public ConnectionCleaner(ServerSocket ss) {
        this.connections = new ArrayList<>(0);
        this.ss = ss;
    }

    public ArrayList<Socket> getSockets() {
        return this.connections;
    }

    /**
     * Auto-called: Checks on every iteration if there are active connections and
     * cleans any inactive ones.
     */
    public void run() {
        while (this.active()) {
            System.out.print(""); // why is this here? idk. will the server break without it? yes.
            this.clean();
        }

        try {
            this.ss.close();
        } catch (IOException ignored) {
        } finally {
            System.out.println("Server closed.");
        }
    }

    /**
     * Filters any closed sockets from the list of active connections. Closes
     * iterating to make sure nothing gets added while it is removing from the list.
     * Reopens it when the iteration is done.
     */
    private void clean() {
        while (iterating) {
        }

        this.iterating = true;
        this.connections.removeIf(Socket::isClosed);
        this.iterating = false;
    }

    /**
     * Checks if there are any active connections.
     *
     * @return bool: true if there are any active sockets. false otherwise.
     */
    public boolean active() {
        if (this.starting)
            return true;
        return this.connections.size() > 0;
    }

    /**
     * Adds the new socket to the list of active connections.
     * 
     * @param socket the new socket to add.
     */
    public void addSocket(Socket socket) {
        this.starting = false;

        while (iterating) {
        }

        this.iterating = true;
        this.connections.add(socket);
        this.iterating = false;
    }
}
