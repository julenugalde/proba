package eus.julenugalde.sandbox.designpatterns.bridge;

/** prueba del patrón de diseño Bridge. Extraido de stackoverflow */
public class BridgeTest {

	public static void main(String[] args) {
		//Diferentes vehiculos con cambio manual
		Gear gear = new ManualGear();
		Vehicle vehicle = new Car(gear);
		vehicle.addGear();
		
		vehicle = new Van(gear);
		vehicle.addGear();
		
		vehicle = new Truck(gear);
		vehicle.addGear();
		
		//Ahora el cambio es automático
		gear = new AutoGear();
		vehicle = new Car(gear);
		vehicle.addGear();
		
		vehicle = new Van(gear);
		vehicle.addGear();
		
		vehicle = new Truck(gear);
		vehicle.addGear();
	}

}
