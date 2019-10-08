package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0; // burkes til � holde kontroll p� hvor neste gpspoint skal settes inn

	public GPSData(int antall) {

		// TODO - START
		
		gpspoints = new GPSPoint[antall];
		
		// TODO - SLUTT
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = false;

		// TODO - START
		
		if (antall < gpspoints.length) {
			gpspoints[antall] = gpspoint;
			antall++;
			inserted = true;
			
		}
		return inserted;
		// TODO - SLUTT
	}
	
	

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		GPSPoint gpspoint = GPSDataConverter.convert(time, latitude, longitude, elevation);

		// TODO - START
		
		return insertGPS(GPSDataConverter.convert(time, latitude, longitude, elevation));

		// TODO - SLUTT
		
	}

	public void print() {

	//	System.out.println("====== Konvertert GPS Data - START ======");

		// TODO - START

		StringBuilder sb = new StringBuilder( "====== Konvertert GPS Data - START ======\n");
		
		for (var point : gpspoints) {
			sb.append(point.toString());
		}
		
		// TODO - SLUTT
		
		sb.append("====== Konvertert GPS Data - SLUTT ======");
		System.out.println(sb.toString());
		

	}
}
