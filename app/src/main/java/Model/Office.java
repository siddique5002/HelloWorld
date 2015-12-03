package Model;

import java.io.Serializable;

/**
 * Created by Siddique on 12/1/2015.
 */
public class Office implements Serializable {
    private static final long id = 1L;
    private String Officename;
    private String address;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String fax;
    private String latitude;
    private String longitude;
    private String Image;
    private double Distance;

    public double getDistance() {
        return Distance;
    }

    public void setDistance(double distance) {
        Distance = distance;
    }

    public static long getId() {
        return id;
    }

    public String getOfficename() {
        return Officename;
    }

    public void setOfficename(String officename) {
        Officename = officename;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
