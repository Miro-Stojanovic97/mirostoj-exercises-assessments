// SUMMER CAMP
// Campers at summer camp can choose one of three afternoon activities:
// swimming, archery, or pottery.
// Swimmers must use the buddy system. If there's not an even number of swimmers,
// swimming is canceled and swimmers are reallocated to archery and pottery with
// the following rules:
// - add swimmers to the activity with the least participants until numbers are even
// - then distribute swimmers evenly, starting with pottery first.
// If there are an even number of swimmers, everyone gets their preferred activity.


/* Scenarios      | 01 | 02 | 03 | 04 | 05 | 06 |
-------------------------------------------------
starting swimmers |  9 |  6 |  5 | 11 | 99 |  3 |
starting archers  | 10 | 13 | 20 |  0 |  3 |  3 |
starting potters  | 12 | 21 |  5 | 11 |  7 |  3 |
final swimmers    |  0 |  6 |  0 |  0 |  0 |  0 |
final archers     | 15 | 13 | 20 | 11 | 54 |  4 |
final potters     | 16 | 21 | 10 | 11 | 55 |  5 |
*/

// Change these numbers to test each scenario.

// Write your distribution code here.
// One set of code must work for all scenarios.
function summerCamp(swimmersStart, archersStart, pottersStart) {
    
    swimmers = swimmersStart;
    archers = archersStart;
    potters = pottersStart;

    if (swimmersStart % 2 !== 0) {

        if (pottersStart > archersStart) {
            swimmers = swimmersStart - (pottersStart - archersStart);
            archers = pottersStart;
            potters = pottersStart;
        } else {
            swimmers = swimmersStart - (archersStart - pottersStart);
            potters = archersStart;
            archers = archersStart;
        }

        if (swimmers > 0) {
            while (swimmers > 0) {
                swimmers--;
                potters++;
                if (swimmers == 0) {
                    break;
                }
                swimmers--;
                archers++;
            }
        } else {
            while (swimmers < 0) {
                if (archersStart > pottersStart) {
                swimmers++;
                potters--;
                } else {
                swimmers++;
                archers--;
                }
            }
        }
        swimmers = 0;
    }

    // Confirm results for each scenario.
    console.log(`Swimmers: ${swimmers}`);
    console.log(`Archers: ${archers}`);
    console.log(`Potters: ${potters}`);
    console.log('-------------------');
}

summerCamp(9,10,12);
summerCamp(6,13,21);
summerCamp(5, 20, 5);
summerCamp(11,0,11);
summerCamp(99,3,7);
summerCamp(3,3,3);
