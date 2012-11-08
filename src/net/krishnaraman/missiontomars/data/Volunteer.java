package net.krishnaraman.missiontomars.data;

public class Volunteer {
    public String id;
    public String email;
    public String name;
    public Boolean mars_born;
    public Integer avoid_people;
    public Integer locked_up;
    public Integer round_trip;
    public Integer like_brown;
    public LatLng lat_long;
    public Integer score;
    public Double percentile;

    public Volunteer(String id, String email, String name, Boolean mars_born, Integer avoid_people, 
    		Integer locked_up, Integer round_trip, Integer like_brown, LatLng lat_long, Integer score,
    		Double percentile) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.mars_born = mars_born;
        this.avoid_people = avoid_people;
        this.locked_up = locked_up;
        this.round_trip = round_trip;
        this.like_brown = like_brown;
        this.lat_long = lat_long;
        this.score = score;
        this.percentile = percentile;
    }

    @Override
    public String toString() {
        return email;
    }
}