package Vehicles;

public class GameGlider extends AirVehicle implements Non_Motorized{
    private String power_source;
    private char energy_score;
    /**
     * Class constructors 
     */
    public GameGlider(String c){
        super("toy",c, 10, 0, "civil");
    }
    public GameGlider(GameGlider g){
        super(g);
        this.power_source=g.power_source;
        this.energy_score=g.energy_score;
    }

    public void PowerSource(String p_s){
        p_s="manual";
        this.power_source=p_s;
    }

    public void EnergyScore(char e_s){
        e_s='A';
        this.energy_score=e_s;
    }

    public String get_type(){return "Game Glider";}
    public String toString(){
        return super.toString() + " power source: " + this.power_source + ", energy score: " + this.energy_score;
    }
    
    public boolean equals(Object other){
        boolean val=false;
        if(other instanceof GameGlider){
            val=((super.equals(other)) && (this.power_source.equals(((GameGlider)other).power_source)) && (this.energy_score==((GameGlider) other).energy_score));
        }
        return val;
    }
}
