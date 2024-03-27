import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import client.Client;
public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Create four instances of the client and submit them to the executor
        for (int i = 0; i < 4; i++) {
            executor.submit(new ClientRunnable());
        }

        // Shutdown the executor once all tasks are completed
        executor.shutdown();
    }
}

class ClientRunnable implements Runnable {
    @Override
    public void run() {
        Client.main(null); // Call the main method of the Client class
    }
}
