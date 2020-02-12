package google;

public class Distance {
	
	static final double R = 6372;
	double fi;
	double p,d;

	
	public static void main(String[] args) {
	
		Distance  distance = new Distance();
		System.out.println("Cristiano - Uni : ");
		distance.calculate(41.85545800000001 , 12.6228887 , 41.7591405 , 12.2381331);
		System.out.println("Ale - Uni : ");
		distance.calculate(41.85545800000001 , 12.6228887 ,  41.8315458 , 12.78342);
	}

	public void calculate(double e , double f , double g , double h) {
		
		double latAlfa = Math.PI * e / 180;
		double lngAlfa = Math.PI * f / 180;
		double latBeta = Math.PI * g / 180;
		double lngBeta = Math.PI * h / 180;
		
		fi = Math.abs(lngAlfa - lngBeta);

		p = Math.acos(Math.sin(latBeta)*Math.sin(latAlfa) + Math.cos(latBeta)*Math.cos(latAlfa)*Math.cos(fi));
		
		System.out.println("Distance = " + p*R + "KM");
		
	}
}
