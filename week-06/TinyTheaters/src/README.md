copy and pasted from last project

## Database Planning:

<!-- Class Diagram / Charting of the Application Development Project-->
### *Class Diagram*
```plantuml
@startuml
skinparam linetype poly
skinparam backgroundColor sandybrown
skinparam ClassBackgroundColor white
skinparam PackageBackgroundColor azure

CUSTOMER --x TICKET
THEATER --x TICKET

package "CUSTOMER" {
}
package "THEATER" {

}
package "TICKET" {
   
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
* Ramsey Theater Company Database Planning  [14-hrs]
** 1)  DDL Development  [3-hrs]
*** 1.1)  Create Database [1-hr]
*** 1.2)  Create Diagram / Planning [2-hr]
** 2)  DML Development  [5-hrs]
*** 2.1)  Inserts  [3-hrs]
*** 2.2)  Updates  [1-hrs]
*** 2.3)  Deletions   [1-hrs]
** 3) DQL Development [6-hrs]
*** 3.1)  Required Queries  [4-hrs]
*** 3.2)  Stretch Goals  [2-hrs]
@endwbs
```

<!-- Gantt Charting the Application Development Project -->
### *Gantt Chart*
```plantuml
@startgantt
printscale daily zoom 2
title Work Breakdown - Hours

/' Days in Gantt format below = Hours in project IRL '/

[Total Project Hours] lasts 13 days
[Total Project Hours] is colored in orange/black
[Total Project Hours] is 0% complete
--WORK BREAKDOWN --
[DDL] lasts 3 days
[DDL] is colored in lightblue/blue
[DDL] is 100% complete
    [1.1] lasts 1 days
        [1.1] is colored in silver/black
        [1.1] is 100% completed
    [1.2] starts after [1.1]'s end and lasts 2 days
        [1.2] is colored in silver/black
        [1.2] is 100% completed
[DDL Complete] happens at [1.2]'s end

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