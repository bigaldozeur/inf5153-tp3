package edu.uqam.inf5153.tp3.domaine;

//ISO/IEC 5218
public enum GENRE {
	   NOT_KNOWN(0), 
	   MALE(1), 
	   FEMALE(2), 
	   NOT_APPLICABLE(9);

	    private final int value;
	    GENRE(int id) { this.value = id; }
	    public int getValue() { return value; }
	    
	    public String toString() {
	    	String genre;
	    	switch(value)
	    	{
	    		case 0:	genre = "Inconnu";
	    			break;
	    		case 1: genre = "Masculin";
	    			break;
	    		case 2: genre = "Fémimin";
	    			break;
	    		case 9: genre = "Non applicable";
	    			break;
	    		default: genre = "Genre inexistant";
	    			break;	    	
	    	}
	    	return genre;
	    }
}
