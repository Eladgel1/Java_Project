package Vehicles;


public class LandVehicle extends Vehicle implements LandVehicleBehavior{
    private int wheels;
    private String RoadType;
    /**
     * Class constructors 
     */
    public LandVehicle(String m,String c, int speed, int passengers,int w,String r_type) {
        super(m,c, speed, passengers);
        this.wheels=w;
        this.RoadType=r_type;
    }
    public LandVehicle(LandVehicle l_v){
        super(l_v);
        this.wheels=l_v.wheels;
        this.RoadType=l_v.RoadType;
    }

    public int get_wheels(){
        return this.wheels;
    }

    public String get_road_type(){
        return this.RoadType;
    }

    public void set_wheels(int w){
        this.wheels=w;
    }

    public void set_road_type(String r_t){
        this.RoadType=r_t;
    }

    public String get_type(){return "Land Vehicle";}
    public String toString(){
        String rep=super.toString() + " number of wheels: " + get_wheels() + ",";
        if (this.RoadType.equals("dirt")){
            rep+=" driving on a dirt road.";
        }
        else{
            rep+=" driving on an asphalt road.";
        }
        return rep;
    }

    public boolean equals(Object other){
        boolean val=false;
        if(other instanceof LandVehicle){
            val=((super.equals(other)) && (this.RoadType.equals(((LandVehicle)other).RoadType))&& (this.wheels==((LandVehicle)other).wheels));
        }
        return val;
    }
    
    
}
