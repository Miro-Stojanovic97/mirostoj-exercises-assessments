# Web Field Agent Assessment
## Tasks
* [x] Continue working in the repo from last week's Field Agent API repository OR use the starter code
  * [x] Add a README in the `client` folder with the contents from this file
* [x] Review the requirements (#.# hours)
* [x] Identify any research that I need to do 
### Part 1: Project Setup and Agents List -- 1.5hrs
* [x] Create an `index.html` and `main.js` file as a starting point for your project
* [x] Dislay a list of agents
  * [x] Use `fetch` to `GET` a list of agents from the Field Agent API when the website is first loaded
  * [x] Use HTML and JavaScript to render the agents array
  * [x] Stub out click event handlers for the "Add Agent", "Edit Agent", and "Delete Agent" buttons
**Commit all changes and push to GitHub**
### Part 2: Add Agent and Delete Agent
* [ ] Create a form to add an agent
  * [ ] Add form HTML
  * [ ] Add onsubmit event handler to the form element (be sure to prevent the form from submitting!)
  * [ ] Create an agent object
  * [ ] Use `fetch` to `POST` the new agent's information to the Field Agent API
  * [ ] On success, refresh the agents list, or on failure, display any validation errors from the API in the UI
* [ ] Support deleting agents
  * [ ] Confirm the deletion with the user
  * [ ] Use `fetch` to `DELETE` the agent from the Field Agent API
  * [ ] On success, refresh the agents list
**Commit all changes and push to GitHub**
### Part 3: Edit Agent -- 1hr
* [x] Support editing agents
  * [x] Retrieve the agent to edit
  * [x] Update the form with the agent's property values
  * [x] Update the onsubmit event handler to handle both `POST` and `PUT` requests
  * [x] Set the agent's ID on the agent object
  * [x] Use `fetch` to `PUT` the updated agent's information to the Field Agent API
  * [x] On success, refresh the agents list, or on failure, display any validation errors from the API in the UI
### Part 4: Refinements -- 1hr
* [x] Conditionally render sections of the page
  * [x] Add a state variable to track the current view
  * [x] Add a method to update the current view and conditionally render the list or the form
  * [x] Call the method to update the current where needed
* [x] Add Bootstrap to the `public/index.html` file
  * [x] Add a link to the Bootstrap CSS using the [CDN from the official docs](https://getbootstrap.com/docs/4.6/getting-started/introduction/#css)
  * [x] Add the [`container` CSS class](https://getbootstrap.com/docs/4.6/layout/overview/#containers) to the `<div id="root"></div>` element
* [x] Apply Bootstrap styling
  * [x] Style all buttons
  * [x] Style the agents list
  * [x] Style the form
**Commit all changes and push to GitHub**
## High-Level Requirements
Implement a full CRUD UI for agents.
* Display all agents
* Add an agent
* Update an agent
* Delete an agent
## Technical Requirements
* Always use semantically correct markup.
* With the exception of Bootstrap (or another CSS framework) for styles, don't use any libraries or frameworks.
* Use `fetch` for async HTTP.
* You are not allowed to change the Field Agent HTTP Service or database (unless there's a confirmed bug and your instructor approves).