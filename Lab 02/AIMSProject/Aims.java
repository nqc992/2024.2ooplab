public class Aims {
	public static void main(String[] args) {
		
		Cart anOrder = new Cart();
		
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
		anOrder.addDigitalVideoDisc(dvd1);
		
		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 82, 24.95f);
		anOrder.addDigitalVideoDisc(dvd2);	
		
		DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);
		anOrder.addDigitalVideoDisc(dvd3);
		
		
		System.out.println("Total cost is: ");
		System.out.println(anOrder.totalCost());
		
		DigitalVideoDisc dvd4 = new DigitalVideoDisc("Frozen", "Animation", "Disney", 100, 20.00f);
		anOrder.addDigitalVideoDisc(dvd4);
		System.out.println("Removing disc...");
		anOrder.removeDigitalVideoDisc(dvd4);
		
		System.out.println("Total discs created: " + DigitalVideoDisc.getNbDigitalVideoDisc());
	}
}
