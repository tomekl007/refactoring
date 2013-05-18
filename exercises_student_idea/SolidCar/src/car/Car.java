package car;

import car.parts.AutomaticGearbox;
import car.parts.Engine;
import car.parts.Pedal;
import car.parts.Wheels;

public class Car {

    private final Engine engine;
    private final AutomaticGearbox gearbox;
    private final Pedal accelerator;
    private final Pedal breakPedal;
    private final Wheels wheels;

    public Car(Engine engine, AutomaticGearbox gearbox, Pedal accelerator, Pedal breakPedal, Wheels wheels){
        this.engine = engine;
        this.accelerator = accelerator;
        this.breakPedal = breakPedal;
        this.gearbox = gearbox;
        this.wheels = wheels;
    }

    public void turnEngineOn() {
        engine.turnOn();

        if(!wheels.areSpinning() && gearbox.isDrive() && engine.isOn() && accelerator.isPressed())
            wheels.startSpinning();
    }

    public void turnEngineOff() {
        engine.turnOff();

        if(wheels.areSpinning())
            wheels.stopSpinning();
    }

    public void presBreak(){
        breakPedal.press();
    }

    public void releaseBreak(){
        breakPedal.release();
    }

    public void pressAcc() {
        accelerator.press();

        if(!engine.isOn())
            engine.turnOn();

        if(!wheels.areSpinning() && gearbox.isDrive() && engine.isOn() && accelerator.isPressed())
            wheels.startSpinning();
    }

    public void releaseAcc() {
        accelerator.release();

        if(engine.isOn())
            engine.turnOff();

        if(wheels.areSpinning())
            wheels.stopSpinning();

    }

    public void gearboxToDrive(){
        gearbox.setDrive();

        if(!wheels.areSpinning() && gearbox.isDrive() && engine.isOn() && accelerator.isPressed())
            wheels.startSpinning();
    }

    public void gearboxToPark(){
        gearbox.setPark();

        if(engine.isOn())
            engine.turnOff();

        if(wheels.areSpinning())
            wheels.stopSpinning();
    }
}
