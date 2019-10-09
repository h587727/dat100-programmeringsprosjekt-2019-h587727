package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	 /*
	 Som beregner den totale distansen på ruten som GPS dataene i gpspoints-tabellen angir.
	 Dvs. Metoden må legge sammen avstanden mellom de punktene som utgjør ruten.
	 */
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

	
		// TODO - START

		for (int i = 0; i < gpspoints.length -1; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i+1]);
		}
		return distance;
		// TODO - SLUTT

	}

	// beregn totale hÃ¸ydemeter (i meter)
	public double totalElevation() {

		double deltaElevation = 0;

		// TODO - START
		double hoeydeMeter = 0;
		for (int i = 0; i < gpspoints.length - 1; i++) {
			// elveation 2 - elevation 1 > 0 => vi går oppover
			deltaElevation = gpspoints[i+1].getElevation() - gpspoints[i].getElevation();
			if (deltaElevation > 0) hoeydeMeter += deltaElevation;
		}
		
		return hoeydeMeter;
		// TODO - SLUTT
		
	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {

		
			return gpspoints[gpspoints.length -1].getTime() - gpspoints[0].getTime();
			
		
	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {
		
		// TODO - START		// OPPGAVE - START
		
		var speeds = new double[gpspoints.length - 1];
		
		for (int i = 0; i < speeds.length; i++) {
			speeds[i] = GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
		}
		
		return speeds;

		// TODO - SLUTT

	}
	
	public double maxSpeed() {
		
		double maxSpeed = 0;
		
		// TODO - START
		
	
		for (var speed : speeds()) {
			if (speed > maxSpeed) maxSpeed = speed;
		}
		
		return maxSpeed;
		
		// TODO - SLUTT
		
	}
	// fast = strekning/tid
	public double averageSpeed() {

	// distance (m)
	// totaltime(s)
		

	return totalDistance() / (double)totalTime() * 3.6; //feil i testen.
		
		// TODO - SLUTT
		
	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling,
	 * general 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0
	 * bicycling, 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9
	 * mph, racing or leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph,
	 * racing/not drafting or >19 mph drafting, very fast, racing general 12.0
	 * bicycling, >20 mph, racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjÃ¸res med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;

		// TODO - START
		
		return met(speed) * weight * secs/3600.0;

		// TODO - SLUTT
		
	}
	private double met(double speed) {
		double mph = speed * 0.62;
		if (mph < 10) return 4.0;
		if (mph < 10 && mph <= 12) return 6.0;
		if (mph < 12 && mph <= 14) return 8.0;
		if (mph < 14 && mph <= 16) return 10.0;
		if (mph < 16 && mph <= 20) return 12.0;
		return 16;
		
		
	/*
	 Hastighet	MET
	<10 mph	4.0
	10-12 mph	6.0
	12-14 mph	8.0
	14-16 mph	10.0
	16-20 mph	12.0
	>20 mph	16.0	
	 */
		
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO - START
	
		
		for (int i = 0; i < gpspoints.length - 1; i++) {
			totalkcal += kcal(weight, gpspoints[i+1].getTime() - gpspoints[i].getTime(), speeds()[i]);
		}
		
		return totalkcal;

		// TODO - SLUTT
		
	}
	
	private static double WEIGHT = 80.0;
	
	
	/*
	=================================
 	Total Time     :   00:36:35
	Total distance :      13.74 km
	Total elevation:     210.60 m
	Max speed      :      47.98 km/t
	Average speed  :      22.54 km/t
	Energy         :     742.80 kcal
	===================================
	 */
	
	
	private static final int WIDTH = 16;
	
	public void displayStatistics() {
		
		String SEP = ":";
		System.out.println("==============================================" +
			formatString( "\nTotal Time:") + SEP + GPSUtils.formatTime(totalTime()) +
			formatString( "\ntotal distance") + SEP + GPSUtils.formatDouble(totalDistance()) + " km" +
			formatString( "\ntotal elevation") + SEP + GPSUtils.formatDouble(totalElevation()) + " m" +
			formatString( "\nMax speed") + SEP + GPSUtils.formatDouble(maxSpeed()) + " km/h" + 
			formatString( "\nAverage speed") + SEP + GPSUtils.formatDouble(averageSpeed()) + " km/h" + 
			formatString( "\nEnergy") + SEP + GPSUtils.formatDouble(totalKcal(WEIGHT)) + " kcal" +
			"\n==============================================");
		
		
		// TODO - START

		
		
		// TODO - SLUTT
		
	}
	
	private static String formatString(String s) {
	return s + " ".repeat(WIDTH - s.length());
}
	
	

}
