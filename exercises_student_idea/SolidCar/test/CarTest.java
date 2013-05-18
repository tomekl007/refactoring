import car.Car;
import car.parts.AutomaticGearbox;
import car.parts.Engine;
import car.parts.Pedal;
import car.parts.Wheels;
import manufactures.Pirelli.Pirelli;
import manufactures.ford.FordEngine;
import manufactures.ford.FordGearbox;
import manufactures.toyota.ToyotaPedal;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CarTest {
    Car car;
    Wheels wheels;
    Engine engine;

    @Before
    public void SetUp(){
        engine = new FordEngine();
        AutomaticGearbox gearbox = new FordGearbox();
        Pedal acceleratorPedal = new ToyotaPedal();
        Pedal breakPedal = new ToyotaPedal();

        wheels = new Pirelli();
        car = new Car(engine,gearbox, acceleratorPedal, breakPedal, wheels);
    }

    @Test
    public void wheelsShouldSpin(){

        //when
        car.turnEngineOn();
        car.gearboxToDrive();
        car.pressAcc();

        //then
        assertTrue(wheels.areSpinning());
    }

    @Test
    public void shouldEngineBeWorking(){
        //given
        car.turnEngineOn();
        car.pressAcc();
        assertTrue(engine.isOn());

        //when
        car.releaseAcc();

        //then
        assertFalse(engine.isOn());
    }

    @Test
    public void turnEngineOffOnPark(){
        //given
        car.turnEngineOn();
        car.gearboxToDrive();
        assertTrue(engine.isOn());

        //when
        car.gearboxToPark();

        //then
        assertFalse(engine.isOn());
    }
}
