package manufactures.toyota;

import car.parts.Pedal;

public class ToyotaPedal implements Pedal {

    boolean state;

    @Override
    public void press() {
        state = true;
        System.out.println("Pedal pressed");
    }

    @Override
    public void release() {
        state = false;
        System.out.println("Pedal released");
    }

    @Override
    public boolean isPressed() {
        return state;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
