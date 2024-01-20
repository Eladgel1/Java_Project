package Vehicles;


public abstract class Vehicle{
    private String model;
    private String color;
    private int km_traveled;
    private int max_speed;
    private int max_passengers;

    /**
     * Class constructors 
     */
    public Vehicle(String m,String c,int speed,int passengers){
        this.model=m;
        this.color=c;
        this.km_traveled=0;
        this.max_speed=speed;
        this.max_passengers=passengers;
    }
    public Vehicle(Vehicle v){
            this.model = v.model;
            this.color=v.color;
            this.km_traveled = v.km_traveled;
            this.max_speed = v.max_speed;
            this.max_passengers = v.max_passengers;
    }
    public String get_model(){
        return this.model;
    }

    public String get_color(){return this.color;}

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

    public void set_color(String c){
        this.color=c;
    }

    public void set_km(int km){
        this.km_traveled=km;
    }
    
    /**
     * This function sets the max speed of the vehicle
     * @param s
     *        the s is displayed as an integer
     */
    public void set_MaxSpeed(int s){
        this.max_speed=s;
    }

    public void set_MaxPass(int p){
        this.max_passengers=p;
    }

    public abstract String get_type();
    public String toString(){
        return "Model: " + get_model() + " ,Color:" + get_color() + " ,Traveled: " + get_km() + " Km,Max speed of " + get_MaxSpeed() + " Mph, can carry max of " + get_MaxPass() + " people.";
    }

    
    public boolean equals(Object other){
        boolean val=false;
        if(other instanceof Vehicle){
            val=((this.model.equals(((Vehicle)other).model)) && (this.km_traveled==((Vehicle)other).km_traveled)
                    && (this.max_speed==((Vehicle)other).max_speed)&&(this.max_passengers==((Vehicle)other).max_passengers));
        }
        return val;
    }
}
