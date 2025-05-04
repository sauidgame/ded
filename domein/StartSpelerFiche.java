package domein;

public class StartSpelerFiche extends Bonusfiche{

	public StartSpelerFiche() {
		super();
		this.setWaarde(getWaarde());
	}
	
	@Override 
	public void setWaarde(int waarde) {
		waarde = 0;
	}

}
