package ru.netology.entity;

public class Location {

    private final String city;

    private final Country country;

    private final String street;

    private final int builing;

    public Location(String city, Country country, String street, int builing) {
        this.city = city;
        this.country = country;
        this.street = street;
        this.builing = builing;
    }

    public String getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

    public String getStreet() {
        return street;
    }

    public int getBuiling() {
        return builing;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        Location c = (Location) obj;
            return (((this.getCity()==null&(c.getCity())==null)||this.getCity().equals(c.getCity()))
                    && ((this.getCountry()==null&(c.getCountry())==null)||this.getCountry().equals(c.getCountry()))
                    && ((this.getStreet()==null&(c.getStreet())==null)||this.getStreet().equals(c.getStreet())))
                    && (this.getBuiling()==c.getBuiling());
    }

}
