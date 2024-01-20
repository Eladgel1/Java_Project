package Vehicles;

public class Frigate extends MarineVehicle implements Motorized{
    private int average_fuel;
    private int engine;
    /**
     * Class constructors 
     */
    public Frigate(String m,String c, int speed, int passengers,String f,boolean w){
        super(m,c,speed,passengers,f,w);
    }
    public Frigate(Frigate f){
        super(f);
        this.engine=f.engine;
        this.average_fuel=f.average_fuel;
    }

    public int get_avg_fuel() {
        return this.average_fuel;
    }
    public void set_avg_fuel(int f){
        f=500;
        this.average_fuel=f;
    }

    public void Engine_LifeTime(int e){
        e=4;
        this.engine=e;
    }
    public String get_type(){return "Frigate";}
    public String toString(){
        return super.toString() + " Engine : " + get_avg_fuel() + "L, lifetime of " + this.engine + " years.";
    }
    public boolean equals(Object other) {
        boolean val = false;
        if (other instanceof Frigate) {
            val = ((super.equals(other)) && (this.engine == ((Frigate) other).engine) && (this.average_fuel == ((Frigate) other).average_fuel));
        }
        return val;
    }
}
