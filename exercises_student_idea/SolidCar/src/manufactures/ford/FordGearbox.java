package manufactures.ford;

import car.parts.AutomaticGearbox;

public class FordGearbox implements AutomaticGearbox {

    private boolean drive;

    @Override
    public void setDrive() {
        drive = true;
        System.out.println("Ford automatic gearbox set to Drive");
    }

    @Override
    public void setPark() {
        drive = false;
        System.out.println("Ford automatic gearbox set to Park");
    }

    @Override
    public boolean isDrive() {
        return drive;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
