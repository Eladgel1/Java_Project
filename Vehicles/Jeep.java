package Vehicles;

public class Jeep extends LandVehicle implements Motorized,Commercial {
    private int average_fuel;
    private int engine;
    private String license_type;
    /**
     * Class constructors 
     */
    public Jeep(String m,String c ,int speed) {
        super(m,c,speed, 5, 4, "dirt");
    }
    public Jeep(Jeep j){
        super(j);
        this.license_type=new String(j.license_type);
        this.engine=j.engine;
        this.average_fuel=j.average_fuel;
    }
    public int get_avg_fuel() {
        return this.average_fuel;
    }

    public void set_avg_fuel(int f){
        this.average_fuel=f;
    }

    public void Engine_LifeTime(int e){
        this.engine=e;
    }

    public void LicenseType(String L_Type){
        L_Type="MINI";
        this.license_type=L_Type;
    }
    public String get_type(){return "Jeep";}

    public String toString(){
        return super.toString() + " This jeep is a MINI commercial vehicle. Engine : " + get_avg_fuel() + "L, lifetime of " + this.engine + " years.";
    }
    public boolean equals(Object other){
        boolean val=false;
        if(other instanceof Jeep){
            val=((super.equals(other)) && (this.engine==((Jeep)other).engine) && (this.average_fuel==((Jeep)other).average_fuel) && (this.license_type.equals(((Jeep)other).license_type)));
        }
        return val;
    }
}