import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    String ParkingLotId;
    List<List<Slot>> slots;

    //constructor
    public ParkingLot(String ParkingLotId, int nfloors, int noOfSlotsPerFlr){
        this.ParkingLotId = ParkingLotId;

        slots = new ArrayList<>();//declaration of slots ArrayList
        for(int i = 0; i<nfloors; i++){
            
            //for every floor the the first slot is for a truck and the next two slots for bikes
            slots.add(new ArrayList<>());
            List<Slot> floorSlots = slots.get(i);
            floorSlots.add(new Slot("truck"));
            floorSlots.add(new Slot("bike"));
            floorSlots.add(new Slot("bike"));
            
            //all the other slots are for cars only
            for(int j = 3; j<noOfSlotsPerFlr; j++){
                slots.get(i).add(new Slot("car"));
            }

        }
    }
    
    public String parkVehicle(String type, String regNo, String color){
        
        Vehicle vehicle = new Vehicle(type, regNo, color);

        for(int i = 0; i < slots.size(); i++){
            for(int j = 0; j < slots.get(i).size(); j++){
                Slot slot = slots.get(i).get(j);
                if(slot.type == type && slot.vehicle == null){
                    slot.vehicle = vehicle;
                    slot.ticketId = generateTicketId(i+1, j+1);//instace methods within the same class can be called without needing an instance, this is a common feature of OOPs
                    return slot.ticketId;
                }
            }
        }
        System.out.println("No slot avialable for given type");
        return null;
    }
    
    private String generateTicketId(int flr, int slno){
        return ParkingLotId + "_" + flr + "_" + slno;
    }

    public void  unPark(String ticketId){
        String[] extract = ticketId.split("_");
        int flr_idx;
        int slot_idx;
        try {
            flr_idx = Integer.parseInt(extract[1]) - 1;
            slot_idx = Integer.parseInt(extract[2]) - 1;
        } catch (Exception e) {
            System.out.println("invalid ticket Id");
            return;
        }
        
        for (int i=0; i<slots.size(); i++){
            for(int j = 0; j<slots.get(i).size(); j++){
                if(i == flr_idx && j == slot_idx){
                    Slot slot = slots.get(i).get(j);
                    slot.vehicle = null;
                    slot.ticketId =null;
                    System.out.println("Vehicle Unplarked");
                }
            }
        }
    }

    int getNoofOpenSlots(String type){
        int count = 0;
        for(List<Slot> floor : slots){
            for(Slot slot: floor){
                if(slot.vehicle == null && slot.type.equals(type)) count++;
            }
        }
        return count;
    }
    
    void displayOpenSlots(String type){
        for(int i = 0;i < slots.size(); i++){
            for(int j = 0; j<slots.get(i).size(); j++){
                Slot slot = slots.get(i).get(j);
                if(slot.vehicle == null && slot.type.equals(type))
                    System.out.println("Floor - "+(i+1)+" Slot - "+(j+1));
            }
        }
    }
    void displayOccupiedSlots(String type){
        for(int i = 0;i < slots.size(); i++){
            for(int j = 0; j<slots.get(i).size(); j++){
                Slot slot = slots.get(i).get(j);
                if(slot.vehicle != null && slot.type.equals(type))
                    System.out.println("Floor - "+(i+1)+" Slot - "+(j+1));
            }
        }
    }
}
