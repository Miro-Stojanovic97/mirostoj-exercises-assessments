copy and pasted from last project

## Application Planning:

<!-- Class Diagram / Charting of the Application Development Project-->
### *Class Diagram*
```plantuml
@startuml
skinparam linetype poly
skinparam backgroundColor sandybrown
skinparam ClassBackgroundColor white
skinparam PackageBackgroundColor azure


MODELS.Reservation <-- UI.Controller
UI.Controller --> DOMAIN.ReservationService
UI.Controller --> DOMAIN.HostService
UI.Controller --> DOMAIN.GuestService
DOMAIN.ReservationService ---> DATA.ReservationRepository
DOMAIN.HostService ---> DATA.HostRepository
DOMAIN.GuestService ---> DATA.GuestRepository

package "MODELS" {
Host . Reservation 
Reservation . Guest
    class Guest {
    tbd
    }
    class Host {
    tbd
    }
    class Reservation {
    tbd
    }
}
package "UI" {
Controller .> View
View . ConsoleIO
ConsoleIO . MenuOption
    class ConsoleIO {
    tbd
    }
    class Controller {
    - ReservationService reservationService
    - HostService hostService 
    - GuestService guestService
    - View view
    tbd
    }
    enum MenuOption {
    tbd
    }
    class View {
    tbd
    }
}
package "DOMAIN" {
    class GuestService {
    tbd
    }
    class HostService {
    tbd
    }
    class ReservationService {
    tbd
    }
    class Result {
    tbd
    }  
}
package "DATA" {
    GuestRepository .. GuestFileRepository
    HostRepository .. HostFileRepository
    ReservationRepository .. ReservationFileRepository
    
    class DataAccessException {
    tbd
    }
    class GuestFileRepository {
    tbd
    }
    interface GuestRepository {
    tbd
    }
    class HostFileRepository {
    tbd
    }
    interface HostRepository {
    tbd
    }
    class ReservationFileRepository {
    tbd
    }
    interface ReservationRepository {
    tbd
    }
}

@enduml
```

<!-- Work Breakdown Structure of the Application Development Project-->
### *Work Breakdown*
```plantuml
@startwbs
skinparam linetype poly
skinparam backgroundColor snow

<style>
node {
MaximumWidth 350
}
</style>
* Don't Wreck My House  [22-hrs]
** 1)  Front End  [8-hrs]
*** 1.1)  Create App, Controller, MenuOptions, View, and ConsoleIO classes/enum  [2-hrs]
*** 1.2)  Create UI methods/logic [6-hrs]
**** 1.2.1)  Create ConsoleIO Methods  [2-hrs]
**** 1.2.2)  Create Controller Methods  [2-hrs]
**** 1.2.3)  Create View Methods  [2-hrs]
*** 1.3)  Implement Stretch Goals in Front-End  [0-hrs]
** 2)  Back End  [14-hrs]
*** 2.1)  Configure POM.xml and Resources  [1-hrs]
*** 2.2)  Create Guest, Host, and Reservation models  [3-hrs]
*** 2.3)  Create Data and Domain Layer, Implement CRUD Operations  [6-hrs]
**** 2.3.1)  Implement methods in Repository classes  [3-hrs]
**** 2.3.2)  Implement methods in Service classes  [3-hrs]
*** 2.4)  Application Testing  [4-hrs]
*** 2.5)  Implement Stretch Goals in Back-End  [0-hrs]
@endwbs
```

<!-- Gantt Charting the Application Development Project -->
### *Gantt Chart*
```plantuml
@startgantt
printscale daily zoom 2
title Work Breakdown - Hours

/' Days in Gantt format below = Hours in project IRL '/

[Total Project Hours] lasts 22 days
[Total Project Hours] is colored in orange/black
[Total Project Hours] is 99% complete
--WORK BREAKDOWN --
[Front End] lasts 12 days
[Front End] is colored in lightblue/blue
[Front End] is 100% complete
    [1.1] lasts 2 days
        [1.1] is colored in silver/black
        [1.1] is 100% completed
    [1.2] starts 4 days after [1.1]'s end and lasts 6 days
        [1.2] is colored in silver/black
        [1.2] is 100% completed
    [1.3] starts at [1.2]'s end and lasts 1 days
        [1.3] is colored in silver/black
        [1.3] is 0% completed
[Front End Complete] happens at [1.2]'s end

[Back End] starts at [1.1]'s end and lasts 20 days
[Back End] is colored in lightgreen/green
[Back End] is 100% completed
    [2.1] starts at [Back End]'s start and lasts 1 days
        [2.1] is colored in silver/black
        [2.1] is 100% complete
    [2.2] starts at [2.1]'s end and lasts 3 days
        [2.2] is colored in silver/black
        [2.2] is 100% complete
    [2.3] starts 6 days after [2.2]'s end and lasts 6 days
        [2.3] is colored in silver/black
        [2.3] is 100% complete
    [2.4] starts at [2.3]'s end and lasts 4 days
        [2.4] is colored in silver/black
        [2.4] is 100% complete
    [2.5] starts at [2.4]'s end and lasts 1 days
        [2.5] is colored in silver/black
        [2.5] is 0% complete
[Back End Complete] happens at [2.4]'s end

@endgantt
```