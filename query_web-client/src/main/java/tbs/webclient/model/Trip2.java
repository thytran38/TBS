package tbs.webclient.model;

import org.springframework.data.annotation.Id;


public class Trip2 {

    private static final long serialVersionUID = 1L;

    @Id
    public String tripId;

    public String tripName;
    public String departLoca;
    public String desLoca;
    public String departStation;
    public String busId;
    public String busBrand;
    public String seatAvailable;
    public String price;
    public String startDatetime;
    public String arrivalDatetime;

//    Used to put these two annotations for tripID variable.
//    @SequenceGenerator(name="trip_seq", sequenceName = "trip_id_seq")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trip_seq")

    public Trip2(){
        tripId = "0000";
    }

    public Trip2(String idTrip, String nameTrip, String priceTrip, String locaDepart, String locaDes, String startTime, String arrivalTime, String busid){
        this.tripId = idTrip;
        this.tripName = nameTrip;
        this.price = priceTrip;
        this.departLoca = locaDepart;
        this.desLoca = locaDes;
        this.busId = busid;
        this.startDatetime = startTime;
        this.arrivalDatetime = arrivalTime;
    }


    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getDepartLoca() {
        return departLoca;
    }

    public void setDepartLoca(String departLoca) {
        this.departLoca = departLoca;
    }

    public String getDesLoca() {
        return desLoca;
    }

    public void setDesLoca(String desLoca) {
        this.desLoca = desLoca;
    }

    public String getDepartStation() {
        return departStation;
    }

    public void setDepartStation(String departStation) {
        this.departStation = departStation;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getBusBrand() {
        return busBrand;
    }

    public void setBusBrand(String busBrand) {
        this.busBrand = busBrand;
    }

    public String getSeatAvailable() {
        return seatAvailable;
    }

    public void setSeatAvailable(String seatAvailable) {
        this.seatAvailable = seatAvailable;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getArrivalDatetime() {
        return arrivalDatetime;
    }

    public void setArrivalDatetime(String arrivalDatetime) {
        this.arrivalDatetime = arrivalDatetime;
    }
}
