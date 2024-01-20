package Vehicles;


public class MarineVehicle extends Vehicle implements MarineVehicleBehavior{
    private String flag;
    private boolean wind;
    /**
     * Class constructors 
     */
    public MarineVehicle(String m,String c, int speed, int passengers,String f,boolean w) {
        super(m,c,speed,passengers);
        this.flag=f;
        this.wind=w;
    }
    public MarineVehicle(MarineVehicle m_v){
        super(m_v);
        this.wind= m_v.wind;
        this.flag= m_v.flag;
    }
    public String get_state_flag(){
        return this.flag;
    }

    public boolean get_wind_direction(){
        return this.wind;
    }

    public void set_state_flag(String f){
        this.flag=f;
    }

    public void set_wind_direction(boolean w){
        this.wind=w;
    }

    public String get_type(){return "Marine Vehicle";}

    public String toString(){
        String rep=super.toString() + " Under " + get_state_flag() + " flag,";
        if (!get_wind_direction()){
            rep+=" against the wind.";
        }
        else{
            rep+=" with the wind.";
        }
        return rep;
    }
    public boolean equals(Object other){
        boolean val=false;
        if(other instanceof MarineVehicle){
            val=((super.equals(other))&&(this.flag.equals(((MarineVehicle)other).flag))&&(this.wind==((MarineVehicle)other).wind));
        }
        return val;
    }
}
