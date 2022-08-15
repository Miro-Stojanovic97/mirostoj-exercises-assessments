# React Field Agent 

-----------------------------------------------------------

## Goals
Start a React front-end for the Field Agent HTTP back-end data service from Module 7.

## High-Level Requirements
Implement a full CRUD UI for agents.
* Display all agents.
* Add an agent.
* Update an agent.
* Delete an agent.

## Technical Requirements
* Use `create-react-app`
* Use `fetch` for async HTTP
* The Field Agent HTTP Service or Database should not be modified (unless there is a confirmed bug/change that needs to be made.)
* Use a CSS framework.

<hr>

## Plan
### Primary Tasks ( Σ = 7.00 hrs )
1. Create Agent, Agents, and AddAgent react components (1.0 hrs).
    * Agent component contains a single agent.
    * Agents component contains display for list of agents.
    * AddAgent component contains form fields for adding a new agent.

2. Implement `fetch` http requests in react components (3.00 hrs).

3. Layout and Style the UI using CSS (1.50 hrs).
    * Single page table of agents. 
    * Agent is mapped to each row with button options for editing/deleting. 
    * Bottom of table has button option to add agent.

4. Test/Debug application (1.50 hrs).

---------------------------------------------

### Getting Started with Create React App
In the project directory, you can run:
### `npm start`
Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.
The page will reload when you make changes.\
You may also see any lint errors in the console.
### `npm test`
Launches the test runner in the interactive watch mode.\
### `npm run build`
Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.
### `npm run eject`