package Vehicles;


public class SpyGlider extends AirVehicle implements Non_Motorized{
    private String power_source;
    private char energy_score;
    /**
     * Class constructors 
     */
    public SpyGlider(String c){
        super("protected",c, 50,1, "military");
    }
    public SpyGlider(SpyGlider s){
        super(s);
        this.power_source=new String(s.power_source);
        this.energy_score=s.energy_score;
    }
    public void PowerSource(String p_s){
        this.power_source=p_s;
    }

    public void EnergyScore(char e_s){
        e_s='C';
        this.energy_score=e_s;
    }

    public String get_type(){return "Spy Glider";}

    public String toString(){
        return super.toString() + " power source: " + this.power_source + ", energy score: " + this.energy_score;
    }

    public boolean equals(Object other){
        boolean val=false;
        if(other instanceof SpyGlider){
            val=((super.equals(other)) && (this.power_source.equals(((SpyGlider)other).power_source)) && (this.energy_score==((SpyGlider) other).energy_score));
        }
        return val;
    }
}