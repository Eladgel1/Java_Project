package Vehicles;

public interface MarineVehicleBehavior {
    public String get_state_flag();
    public boolean get_wind_direction();

    public void set_state_flag(String f);

    public void set_wind_direction(boolean w);

}
