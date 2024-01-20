package Vehicles;


public class HybridPlane extends Amphibious implements AirVehicleBehavior,Motorized {
    private AirVehicle A_Vehicle;
    private int average_fuel;
    private int engine;
    private String AirType;
    public HybridPlane(String m,String c, int speed, int passengers,int wheels,String f,boolean wind){
        super(m,c,speed,passengers,wheels,f,wind);
        this.A_Vehicle=new AirVehicle(m,c,speed,passengers,"military");
    }
    public HybridPlane(HybridPlane hp){
        super(hp);
        this.A_Vehicle=new AirVehicle(hp.get_model(),hp.get_color() ,hp.get_MaxSpeed(),hp.get_MaxPass(),hp.get_AirType());
        this.average_fuel=hp.average_fuel;
        this.engine=hp.engine;
    }
    public String get_AirType(){
        return this.AirType;
    }
    public void set_AirType(String type){
        this.AirType=type;
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
    public AirVehicle get_air_attributes(){return A_Vehicle;}

    public String get_type(){return "Hybrid Plane";}

    public String toString(){
        String rep= super.toString();
        rep+=" intended for military use.";
        return rep;
    }
    public boolean equals(Object other){
        boolean val=false;
        if(other instanceof HybridPlane){
            val= ((super.equals(other)) && (this.A_Vehicle.equals(other)) &&(this.average_fuel==((HybridPlane)other).average_fuel) && (this.engine==((HybridPlane)other).engine));
        }
        return val;
    }
}
