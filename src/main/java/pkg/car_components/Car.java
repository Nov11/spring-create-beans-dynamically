package pkg.car_components;

import org.springframework.beans.factory.support.ManagedList;

public class Car {
    private Wheel wheel;
    private Engine engine;
    private Window window;

    private ManagedList<Engine> engineList;

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public void startEngine() {
        if (engine != null) {
            System.out.println("single engine");
            engine.start();
        }

        if (engineList != null) {
            System.out.println("engine list");
            for (Engine e : engineList) {
                e.start();
            }
        }
    }

    public ManagedList<Engine> getEngineList() {
        return engineList;
    }

    public void setEngineList(ManagedList<Engine> engineList) {
        this.engineList = engineList;
    }
}
