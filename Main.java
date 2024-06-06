public class Main {
    public static void main(String[] args){
        int nFloors = 4;
        int nSlotsPerFloor = 6;
        ParkingLot parkingLot = new ParkingLot("PR1234", nFloors, nSlotsPerFloor);

        System.out.println(parkingLot.getNoofOpenSlots("car"));

        String ticket1 = parkingLot.parkVehicle("car", "MH-03", "red");
        String ticket2 = parkingLot.parkVehicle("car", "MH-04", "purple");

        parkingLot.displayOccupiedSlots("car");

        //similarly we can check for other methods too
    }
}
