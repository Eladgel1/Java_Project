package Vehicles;

public class CruiseShip extends MarineVehicle implements Motorized,Commercial{
    private int average_fuel;
    private int engine;
    private String license_type;
    public CruiseShip(String m,String c, int speed, int passengers,String f){
        super(m,c,speed,passengers,f,true);
    }
    public CruiseShip(CruiseShip cs){
        super(cs);
        this.average_fuel=cs.average_fuel;
        this.engine=cs.engine;
        this.license_type=cs.license_type;
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
    public void LicenseType(String L_Type){
        L_Type="Unlimited";
        this.license_type=L_Type;
    }
    public String get_type(){return "Cruise Ship";}
    public String toString(){
        return super.toString() + " This Cruise Ship is an Unlimited commercial vehicle. Engine : " + get_avg_fuel() + "L, lifetime of " + this.engine + " years.";
    }
    public boolean equals(Object other) {
        boolean val = false;
        if (other instanceof CruiseShip) {
            val = ((super.equals(other)) && (this.engine == ((CruiseShip) other).engine) && (this.average_fuel == ((CruiseShip) other).average_fuel) && (this.license_type.equals(((CruiseShip)other).license_type)));
        }
        return val;
    }
}
