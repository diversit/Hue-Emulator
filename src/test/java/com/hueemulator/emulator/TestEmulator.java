package com.hueemulator.emulator;

import java.io.IOException;

import com.hueemulator.gui.View;
import com.hueemulator.server.Server;
import org.springframework.util.SocketUtils;


public class TestEmulator {
    private static TestEmulator instance = null;
    private Emulator emulator;
    private Model model;
    private Controller controller;

    final private String fileName = "/config-3bulbs.json";
    
    private boolean isServerRunning=false;
    
    public static final int DEFAULT_PORT_NUMBER = 8888;

    private final int emulatorPort;

    public TestEmulator(int port) {
        emulatorPort = port;
    }

    public int getEmulatorPort() {
        return emulatorPort;
    }

    /**
     * @return The base url on wihch this emulator can be reached.
     */
    public String getBaseUrl() {
        return String.format("http://localhost:%d/api/", emulatorPort);
    }

    /**
     * @return emulator instance on default port (8888)
     */
    public static TestEmulator getInstance() {
        if (instance == null) {
            instance = new TestEmulator(DEFAULT_PORT_NUMBER);
        }
        return instance;
    }

    /**
     * @return emulator on random available port (>=8001).
     *         use {@link #getEmulatorPort()} to find out port on which emulator is started.
     */
    public static TestEmulator getInstanceOnRandomPort() {
        int availableTcpPort = SocketUtils.findAvailableTcpPort(8001);
        return new TestEmulator(availableTcpPort);
    }
    
    public  void startEmulator(boolean headless) throws IOException {
        if (controller!=null) return;

        model = new Model();

        View view = headless ? null : new View();

        controller = new Controller(model, view, null);

        if (!headless) {
            view.getMenuBar().setController(controller);
            view.getGraphicsPanel().setController(controller);
        }

        emulator = new Emulator(controller, null);
        emulator.loadConfiguration(fileName);
        setModel(model);
   
        try {        
            isServerRunning=true;
            emulator.setServer(new Server(model.getBridgeConfiguration(), controller, String.valueOf(emulatorPort)));
        } catch (java.net.BindException e) {
            System.out.println(" **NOT STARTED **  Server already running.    " + e.getMessage());            
        }   

       if (emulator.getServer() != null) {   //   setUp is started before each test.    
            System.out.println("Starting JUnit Test Emulator...");    
            emulator.getServer().getHttpServer().start();
        }
    }

    public void stopEmulator() {
        if (isServerRunning) {
            System.out.println("Stopping emulator.");
            emulator.getServer().getHttpServer().stop(0);
        } else {
            System.out.println("Emulator not running.");
        }
    }

    /**
     * This method is to ensure the Tests are stateless.  This method should be called in the setUp before each test is run so the data is reloaded in the server.
     * i.e. So changing a light state in 1 test does not affect any other tests.
     */
    public void reloadInitialConfig() {
        emulator.loadConfiguration(fileName);
        emulator.getServer().removeContext();
        emulator.getServer().createContext(model.getBridgeConfiguration(), controller);
    }

    public boolean isServerRunning() {
        return isServerRunning;
    }

    public void setServerRunning(boolean isServerRunning) {
        this.isServerRunning = isServerRunning;
    }



    public Emulator getEmulator() {
        return emulator;
    }


    public void setEmulator(Emulator emulator) {
        this.emulator = emulator;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
    
}
