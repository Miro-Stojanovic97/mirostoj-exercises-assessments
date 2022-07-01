//import jdk.swing.interop.SwingInterOpUtils;
import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;

public class Exercise08 {

    public static void main(String[] args) {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        // 1. Instantiate a new HashMap<String, Vehicle> named `twoThousandSix`.
        // 2. Loop through `vehicleMap` and all 2006 vehicles to `twoThousandSix`;
        // 3. Loop through `twoThousandSix` and display all vehicles.
        // (You may want to use your print all method from Exercise03.)
        // 4. How many 2006 vehicles are there? (Expected: 50)

            HashMap<String, Vehicle> twoThousandSix = new HashMap<>();

            //enhanced for loop
            for(Vehicle mm : vehicleMap.values()) {
                if (mm.getYear() == 2006) {
                    twoThousandSix.put(mm.getVin(), mm);
                }
            }

            for (Vehicle mm : twoThousandSix.values()) {
                System.out.println(mm);
            }
        System.out.printf("Count of 2006 Vehicles: %s", twoThousandSix.size());
    }
}
