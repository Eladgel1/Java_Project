package Vehicles;

public class AirVehicle extends Vehicle implements AirVehicleBehavior{
    private String AirType;
    /**
     * Class constructors 
     */
    public AirVehicle(String m, String c, int speed, int passengers,String a_type) {
        super(m, c, speed, passengers);
        this.AirType=a_type;
    }

    public AirVehicle(AirVehicle a_v){
        super(a_v);
        this.AirType=a_v.AirType;
    }

    public String get_AirType(){
        return this.AirType;
    }
    public void set_AirType(String a_type){
        this.AirType=a_type;
    }
    public String get_type(){return "Air Vehicle";}

    public String toString(){
        if(this.AirType.equals("military")){
            return super.toString() + " intended for military use.";
        }
        else{
            return super.toString() + " intended for civil use.";
        }
    }

    public boolean equals(Object other){
        boolean val=false;
        if(other instanceof AirVehicle){
            val=(super.equals(other))&&(this.AirType.equals(((AirVehicle)other).AirType));
        }
        return val;
    }
}