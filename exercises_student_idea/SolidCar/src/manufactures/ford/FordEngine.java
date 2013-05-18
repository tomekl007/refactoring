package manufactures.ford;

import car.parts.Engine;

public class FordEngine implements Engine {

    private boolean state;

    @Override
    public void turnOn() {
        state = true;
        System.out.println("Ford engine turned ON");
    }

    @Override
    public void turnOff() {
        state = false;
        System.out.println("Ford engine turned OFF");
    }

    @Override
    public boolean isOn() {
        return state;
    }
}
