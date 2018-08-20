package gwt.material.amcharts.demo.client.application.home;

public class SampleObject {

    private String country;
    private int litres;

    public SampleObject() {
    }

    public SampleObject(String country, int litres) {
        this.country = country;
        this.litres = litres;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLitres() {
        return litres;
    }

    public void setLitres(int litres) {
        this.litres = litres;
    }
}
