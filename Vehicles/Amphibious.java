package Vehicles;

public class Amphibious extends Vehicle implements LandVehicleBehavior,MarineVehicleBehavior,Motorized{
    private LandVehicle L_Vehicle;
    private MarineVehicle M_Vehicle;
    private int average_fuel;
    private int engine;
    private String model;
    private String color;
    private int km_traveled;
    private int max_speed;
    private int max_passengers;
    public Amphibious(String m,String c, int speed, int passengers,int wheels,String f,boolean wind){
        super(m,c,speed,passengers);
        this.model=m;
        this.max_speed=speed;
        this.max_passengers=passengers;
        this.L_Vehicle=new LandVehicle(m,c,speed,passengers,wheels,"asphalt");
        this.M_Vehicle =new MarineVehicle(m,c,speed,passengers,f,wind);
    }
    public Amphibious(Amphibious amph){
        super(amph);
        this.model= amph.model;
        this.km_traveled=amph.km_traveled;
        this.max_speed=amph.max_speed;
        this.max_passengers=amph.max_passengers;
        this.L_Vehicle=new LandVehicle(amph.model,amph.color,amph.max_speed,amph.max_passengers,amph.get_wheels(),amph.get_road_type());
        this.M_Vehicle=new MarineVehicle(amph.model,amph.color,amph.max_speed,amph.max_passengers,amph.get_state_flag(),amph.get_wind_direction());
        this.average_fuel=amph.average_fuel;
        this.engine=amph.engine;
    }
    public String get_model(){
        return this.model;
    }
    public int get_km(){
        return this.km_traveled;
    }
    public int get_MaxSpeed(){
        return this.max_speed;
    }
    public int get_MaxPass(){
        return this.max_passengers;
    }
    public void set_model(String m){
        this.model=m;
    }
    public void set_km(int km){
        this.km_traveled=km;
    }
    public void set_MaxSpeed(int s){
        this.max_speed=s;
    }
    public void set_MaxPass(int p){
        this.max_passengers=p;
    }
    public int get_wheels(){
        return this.L_Vehicle.get_wheels();
    }
    public String get_road_type(){
        return this.L_Vehicle.get_road_type();
    }
    public String get_state_flag(){
        return this.M_Vehicle.get_state_flag();
    }
    public boolean get_wind_direction(){
        return this.M_Vehicle.get_wind_direction();
    }
    public void set_state_flag(String f){
        this.M_Vehicle.set_state_flag(f);
    }
    public void set_wind_direction(boolean w){
        this.M_Vehicle.set_wind_direction(w);
    }
    public int get_avg_fuel(){
        return this.average_fuel;
    }
    public void set_avg_fuel(int f){
        this.average_fuel=f;
    }
    public void Engine_LifeTime(int e){
        this.engine=e;
    }
    public LandVehicle get_land_attributes(){
        return L_Vehicle;
    }
    public MarineVehicle get_marine_attributes(){
        return M_Vehicle;
    }
    public String get_type(){return "Amphibious";}
    public String toString(){
        String rep= "Model: " + this.model + " ,Traveled: " + this.km_traveled + " Km,Max speed of " + this.max_speed + " Mph, can carry max of " + this.max_passengers + " people." + " number of wheels: " + get_wheels() + "," + " driving on an asphalt road." + " Under " + get_state_flag() + " flag,";
        if (!get_wind_direction()){
            rep+=" against the wind.";
        }
        else{
            rep+=" with the wind.";
        }
        rep+=" Engine : " + this.average_fuel + "L, lifetime of  " + this.engine + " years.";
        return rep;
    }
    public boolean equals(Object other){
        boolean val=false;
        if(other instanceof Amphibious){
            val= ((L_Vehicle.equals(other)) && (M_Vehicle.equals(other)) && (this.average_fuel==((Amphibious)other).average_fuel) && (this.engine==((Amphibious)other).engine));
        }
        return val;
    }
}
