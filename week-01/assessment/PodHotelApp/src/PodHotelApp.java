//Author: Miro Stojanovic
//Module 1 Assessment

//I'm using pod instead of capsule bc both terms are used in the
// industry to describe the same thing, and pod is more concise.

public class PodHotelApp {




}



//Project Outline
    //initiate string array for pods String[]
    //Print Hello/intro to hotel
    //Prompt user for hotelCapacity and read from console
    //Print number of pods vacant - podAvail

    //initiate menu for user
        //print menu, 1. Check in, 2. Check out, 3. View guests, 4. Exit, and to choose option
        //Prompt user to choose an option and read from console
        //Do
            //"1" checkin
                    //checkin/out method
                    //result display method
            //"2" check out
                    //checkin/out method
                    //result display method
            //"3" view guests
                    //room display method
        //while input condition !"4", exit condition
            //"4" exit
                    //print that programs exiting, say bye
        //---------------------------------------------------------main^

        //define method for checking in and checking out
                //enter room number, check if vacant or filled
                //if vacant, ask for persons name then fill the room and print it
                //if filled, find persons name then replace with null value

        //define method for displaying checkin/checkout results
                // if check-out && filled  ->  success prompt.
                // if check-out && empty  ->  fail prompt
                // if check-in && filled  ->  fail prompt
                // if check-in && empty  ->  success prompty

        //define method for displaying rooms, containing the rooms array
            //prompt user for room number
            //prompt 5 above and 5 below

 //



/*Project Requirements
Usage Req's:
    Prompt user for hotelCapacity
        determine podAvail
    May *book new* guest in unassigned #'d pod
    May *check out* guest from assigned pod
    View guests and their pod numbers in groups of 11 (cycling thru if close to 1 or 100

Tech Req's:
    -At start, pods and guests will be represented by a String[] of appropriate size
    -Unoccupied pods are represented by a null array value
    -Occupied pods are represented by the occupants name as a string
    -Must have >1 method, not just Main method
 */