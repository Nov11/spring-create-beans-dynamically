package pkg.car_components;

public class Engine {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void start() {
        System.out.println("start engine:" + name);
    }

    public Engine(String name) {
        this.name = name;
    }

    public Engine() {
    }
}
