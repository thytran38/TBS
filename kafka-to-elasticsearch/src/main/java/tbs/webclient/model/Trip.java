package tbs.webclient.model;


public class Trip{

    private static final long serialVersionUID = 1L;

    public int tripId;

    public String tripName;
    public String departLoca;
    public String desLoca;
    public String departStation;
    public int busId;
    public String busBrand;
    public int seatAvailable;
    public int price;
    public long startDatetime;
    public long arrivalDatetime;

//    Used to put these two annotations for tripID variable.
//    @SequenceGenerator(name="trip_seq", sequenceName = "trip_id_seq")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trip_seq")

    public Trip(){
        tripId = 00000;
    }

    public Trip(int idTrip, String nameTrip, int priceTrip,String locaDepart, String locaDes, long startTime, long arrivalTime, int busid){
        this.tripId = idTrip;
        this.tripName = nameTrip;
        this.price = priceTrip;
        this.departLoca = locaDepart;
        this.desLoca = locaDes;
        this.busId = busid;
        this.startDatetime = startTime;
        this.arrivalDatetime = arrivalTime;
    }


    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
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

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public String getBusBrand() {
        return busBrand;
    }

    public void setBusBrand(String busBrand) {
        this.busBrand = busBrand;
    }

    public int getSeatAvailable() {
        return seatAvailable;
    }

    public void setSeatAvailable(int seatAvailable) {
        this.seatAvailable = seatAvailable;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(long startDatetime) {
        this.startDatetime = startDatetime;
    }

    public long getArrivalDatetime() {
        return arrivalDatetime;
    }

    public void setArrivalDatetime(long arrivalDatetime) {
        this.arrivalDatetime = arrivalDatetime;
    }
}
