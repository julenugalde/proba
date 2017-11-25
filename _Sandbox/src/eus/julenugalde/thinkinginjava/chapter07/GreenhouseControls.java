package eus.julenugalde.thinkinginjava.chapter07;

public class GreenhouseControls extends Controller {
	private boolean light = false;
	private boolean water = false;
	private String thermostat = "Day";
	
	private class LightOn extends Event {
		public LightOn(long eventTime) {
			super(eventTime);
		}
		public void action() {
			System.out.println("Switching on the light...");
			light = true;
		}
		public String description() {
			return "The light is on.";
		}
	}
	
	private class LightOff extends Event {
		public LightOff(long eventTime) {
			super(eventTime);
		}
		public void action() {
			System.out.println("Switching off the light...");
			light = false;
		}
		public String description() {
			return "The light is off.";
		}
	}
	
	private class WaterOn extends Event {
		public WaterOn(long eventTime) {
			super(eventTime);
		}
		public void action() {
			System.out.println("Turning on the water...");
			water = true;
		}
		public String description() {
			return "The water is open.";
		}
	}
	
		private class WaterOff extends Event {
		public WaterOff(long eventTime) {
			super(eventTime);
		}
		public void action() {
			System.out.println("Turning off the water...");
			water = false;
		}
		public String description() {
			return "The water is closed.";
		}
	}
	
	private class ThermostatNight extends Event {
		public ThermostatNight(long eventTime) {
			super(eventTime);
		}
		public void action() {
			System.out.println("Setting the thermostat in night mode...");
			thermostat = "Night";
		}
		public String description() {
			return "Thermostat in night setting.";
		}
	}
	
	private class ThermostatDay extends Event {
		public ThermostatDay(long eventTime) {
			super(eventTime);
		}
		public void action() {
			System.out.println("Setting the thermostat in day mode...");
			thermostat = "Day";
		}
		public String description() {
			return "Thermostat in day setting.";
		}
	}
	
	private int rings;
	private class Bell extends Event {
		public Bell(long eventTime) {
			super(eventTime);
		}
		public void action() {
			System.out.println("Bing!");
			if (--rings > 0) {
				events.add(new Bell(System.currentTimeMillis() + 2000));
			}
		}
		public String description() {
			return "Ring bell";
		}
	}
	
	private class Restart extends Event {
		public Restart(long eventTime) {
			super(eventTime);
		}
		public void action() {
			long tm = System.currentTimeMillis();
			rings = 5;
			events.add(new ThermostatNight(tm));
			events.add(new LightOn(tm + 1000));
			events.add(new LightOff(tm + 2000));
			events.add(new WaterOn(tm + 3000));
			events.add(new WaterOff(tm + 8000));
			events.add(new Bell(tm + 9000));
			events.add(new ThermostatDay(tm + 10000));
			//events.add(new Restart(tm + 20000));			
		}
		public String description() {
			return "Restarting system";
		}
	}
	
	public static void main (String[] args) {
		GreenhouseControls gc = new GreenhouseControls();
		long tm = System.currentTimeMillis();
		gc.addEvent(gc.new Restart(tm));
		gc.run();
	}
}
