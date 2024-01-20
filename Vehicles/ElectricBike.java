package Vehicles;

public class ElectricBike extends LandVehicle implements Motorized {
    private int engine;
    private int average_fuel;
    public ElectricBike(String m,String c, int speed,String r_type){
        super(m,c,speed,1,2,r_type);
    }
    public ElectricBike(ElectricBike e_b){
            super(e_b);
            this.engine=e_b.engine;
            this.average_fuel=e_b.average_fuel;
    }
    public int get_avg_fuel(){return this.average_fuel;}
    public void set_avg_fuel(int f){
        f=20;
        this.average_fuel=f;
    }
    public void Engine_LifeTime(int e){this.engine=e;}
    public String toString(){
        return super.toString() + " Engine : " + this.average_fuel + "L, lifetime of " + this.engine + " years.";
    }
    public String get_type(){return "Electric Bike";}
    public boolean equals(Object other){
        boolean val=false;
        if(other instanceof ElectricBike){
            val=((super.equals(other)) && (this.engine==((ElectricBike)other).engine) && (this.average_fuel==((ElectricBike)other).average_fuel));
        }
        return val;
    }
}
