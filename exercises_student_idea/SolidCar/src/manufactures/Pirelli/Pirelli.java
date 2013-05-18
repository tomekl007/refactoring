package manufactures.Pirelli;

import car.parts.Wheels;

public class Pirelli implements Wheels{

    boolean spinning;

    @Override
    public void startSpinning() {
        spinning = true;
        System.out.println("Wheels are spinning now");
    }

    @Override
    public void stopSpinning() {
        spinning = false;
        System.out.println("Wheels stopped spinning");
    }

    @Override
    public boolean areSpinning() {
        return spinning;
    }
}
