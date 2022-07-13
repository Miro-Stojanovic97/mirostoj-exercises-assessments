# Don't Wreck My House
A **Multi-Layer Application** that allows a user to reserve rooms for guests with a specified host. 
- Created by *Miro Stojanovic*, with specifications and assistance from Dev-10 Instructors.

## Application Specifications:
### General Requirements:
- Application user is an accommodation admin, pairing guests to host's to make reservations
- Admin may _**view existing reservations**_ for a host
- Admin _**create a reservation for a guest**_ w a host
- Admin may _**edit existing reservations**_
- Admin may _**cancel future reservations**_
- Must be Maven project
- Spring Dependency Injection 
- BigDecimal for financial math
- LocalDate for Dates
- All file data must be represented in models 
- Combo of reservation ID + host ID is req'd to uniquely ID a reservation

### Required Features:
 - **View Reservations for Host -**
   - User uniquely identifies host (some way, can be by search, by email, etc.) then show all reservations
   - One idea: Search by state -> drop list of hosts?
   - Have validation for nonexistent hosts, or hosts with no reservations
   - Show useful info for reservation (guest, dates, total$, etc.)
   - Sort reservations in a meaningful way (date? prompt user for choice?)
 - **Make a Reservation for a Guest (as Admin) -**
   - Admin enters value that uniquely ID's guest, and then host
   - Should show hosts future reservations so the right dates can be selected
   - Should enter a start and end date, calculate the total, display a summary, and confirm/save
   - Make sure to calculate given weekday rates and weekend rates
   - Do proper validation for guest, host, dates
 - **Edit a Reservation -**
   - Be able to find a reservation, and edit the dates only
   - Recalculate and display the total, summary, and ask user to confirm
   - Do proper validation for guest, host, dates
 - **Cancel a Reservation -**
   - Cancel a future reservation by finding it then deleting it
   - No canceling reservations that are in the past, only show future ones


### Glossary:
- _**Guest**_: Customer that wants to book a stay (Guest data is provided)
- **_Host_**: Accommodation provider (Host data is provided)
- _**Location:**_ A rental property (one location per 1 host)
- _**Reservation:**_ Day(s) where a Guest has exclusive rights to the Location / Host. 
- _**Admin:**_ The app user. Guests and Hosts don't book their own reservations, the admin does.

### User Story:
- As the administrator and user of the app -
  - I want to be able to easily view reservations and sufficient relevant details associated with them
  - I want to be able to seamlessly make and edit reservations, as well as delete them
  - I want to be able to enter information without fear of minor errors breaking the application

### Stretch Goals:
- Make reservation ID's unique across the entire application
- Allow user to create, edit, and delete guests
- Allow user to create, edit, and delete hosts
- Display reservations for guest
- Display all reservations for a state, city, or zip code
- Implement another set of repo's that store data in JSON format




## Application Planning:
Convert these eventually to the gantt chart below
- Configure POM.xml --> 0.2-hrs 

<!-- Work Breakdown Structure of the Application Development Project-->
### Work Breakdown
```plantuml
@startwbs
<style>
node {
MaximumWidth 300
}
</style>
* Don't Wreck My House*
** 1 Front End
*** 1.1 Front End 1 [xx-hrs]
*** 1.2 Front End 2 [xx-hrs]
*** 1.3 Front End 3 [xx-hrs]
** 2 Back End
*** 2.1 Back End 1 [xx-hrs]
**** 2.1.1 Configure POM.xml [0.2-hrs]
*** 2.2 Back End 2 [xx-hrs]
*** 2.3 Back End 3 [xx-hrs]
@endwbs
```

<!-- Gantt Charting the Application Development Project -->
```plantuml
@startgantt
title Work Breakdown - Hours

/' Days in Gantt format below = Hours in project IRL '/

[Total Project Hours] lasts 24 days
[Total Project Hours] is colored in orange/black
[Total Project Hours] is 40% complete
--WORK BREAKDOWN --
[Front End] lasts 12 days
[Front End] is colored in lightblue/blue
[Front End] is 40% complete
    [1.1] lasts 4 days
        [1.1] is colored in silver/black
        [1.1] is 40% completed
    [1.2] starts at [1.1]'s end and lasts 4 days
        [1.2] is colored in silver/black
        [1.2] is 40% completed
    [1.3] starts at [1.2]'s end and lasts 4 days
        [1.3] is colored in silver/black
        [1.3] is 40% completed
    
[Back End] starts at [1.3]'s end and lasts 12 days
[Back End] is colored in lightgreen/green
[Back End] is 40% completed
    [2.1] starts at [Back End]'s start and lasts 4 days
        [2.1] is colored in silver/black
        [2.1] is 40% complete
        [2.1.1] starts at [2.1]'s start and lasts 1 days
        [2.1.1] is colored in silver/black
        [2.1.1] is 100% complete
    [2.2] starts at [2.1]'s end and lasts 4 days
        [2.2] is colored in silver/black
        [2.2] is 40% complete
    [2.3] starts at [2.2]'s end and lasts 4 days
        [2.3] is colored in silver/black
        [2.3] is 40% complete

@endgantt
```