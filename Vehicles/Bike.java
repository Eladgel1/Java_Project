package Vehicles;
public class Bike extends LandVehicle implements Non_Motorized{
    private String power_source;
    private char energy_score;
    public Bike(String m,String c, int speed, String r_type) {
        super(m,c, speed, 1, 2, r_type);
    }
    public Bike(Bike b){
        super(b);
        this.power_source=b.power_source;
        this.energy_score=b.energy_score;
    }
    public void PowerSource(String p_s){
        p_s="manual";
        this.power_source=p_s;
    }
    public void EnergyScore(char e_s){
        e_s='A';
        this.energy_score=e_s;
    }
    public String toString(){
        return super.toString() + " power source: " + this.power_source + ", energy score: " + this.energy_score;
    }
    public String get_type(){return "Bike";}
    public boolean equals(Object other){
        boolean val=false;
        if(other instanceof Bike){
            val=((super.equals(other)) && (this.power_source.equals(((Bike)other).power_source)) && (this.energy_score==((Bike) other).energy_score));
        }
        return val;
    }
}
