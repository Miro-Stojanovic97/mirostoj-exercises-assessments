
# Solar Farm Client

## Part 1

* [x] Create a new React project with CRA (create-react-app).

* [x] Create a component to display the list of solar panels.

* [x] Import and use Bootstrap for styling your components.

### React Components

* Virtual DOM
  * This looks and behaves similar to (but exactly the same) as the regular DOM
  * Receives the content to render
  * When stuff changes the virtual DOM calculates a "diff" between the current state of the DOM and the new state
  * The "diff" is an absolute minimal set of changes to do to the actual DOM in the browser
* DOM (Document Object Model)
  * Difficult to use
  * If you're careful, you can expose yourself to injection attacks
  * It's SLOW... sometimes REALLY SLOW

### Why React???

* Structure... React gives us a set of opinions to follow
* Scale... bigger projects... bigger dev teams
* Performance... Virtual DOM
* Really popular

_Other front end library and framework options:_

* React
* Angular
* Vue.js
* Svelte
* Next.js
* And many more!

_Break_

* [x] Create a form to add a solar panel.
  * Prevent the page from reloading when the form is submitted... we need to do this because we're developing a SPA (Single Page Application)
  * Review individual change handlers vs a single change handler using object tracking

_Break_

* [x] Support deleting solar panels.

_Break_

* [x] Support editing solar panels.

_Break_

* [x] Conditional rendering... hide/show the separate views

## Part 2

_Add API interactions using Fetch_

* [x] GET

* [x] DELETE

* [x] POST
  * Display errors from the API

* [x] PUT

**MAKE A COPY OF THE PROJECT!!!**



## Part 3

_Introduce client side routing_


What is routing?
  Mapping a request to a specific resource
  Our API is using routing

Server-side rendered apps and routing
  http://localhost:8080/index.html --> index.html

Server-side
  Simplier
  Rendering pages... faster
  There's more network traffic

Client-side
  Complicated
  Rendering pages... often slower... using React helps
  There's less network traffic

Client-side rendered apps and routing
  A way to navigate to components or different pages... the URL changes

"Deep link"

Home
About
Contact

NavBar

/solarpanels
List (SolarPanelList)

_Break_

/solarpanels/add
Add (SolarPanelForm)

/solarpanels/edit/1
Edit (SolarPanelForm)

```
<Route path={["/solarpanels/edit/:id", "/solarpanels/add"]}>
  <SolarPanelForm />
</Route>
```

```
const { id } = useParams();
```






NotFound











Optional

/solarpanels/delete
Delete (SolarPanelDelete)

Menu bootstrap styling
NavLink React Router component (https://v5.reactrouter.com/web/api/NavLink)

Show how to move the form into a component
Move the errors rendering into a component















## Answer any unanswered standup questions

I would love to see the hidden form done with the proper initialized values

I needed to use useEffect in my form to make sure it rendered the panel details for the edit panel choice. Is that pretty normal for standard elements?

I know there is no One right way but there must be some conventions that most of the React community follows.

I wasn't sure how to approach implementing an update feature and ran out of time to do enough research to figure it out.

I was not able to figure out how to delete a panel. I tried to recreate james example approach to deleting but after debugging i found that my child component was not communicating with my parent component.

useParams only use to get an 'ID' to connect or could it be used on any route parameter that is available?

I wasn't sure how to implement interacting with the backend using a JavaScript array instead of fetch.

Can we go over some concrete examples of side effects in React?

I was just curious about this, but from the React Router lesson, I was wondering how client side routing affects the Java back-end. Does it render Java backend obsolete, less useful and/or capable, etc?



The code compiles and run just fine, and when I audit fix it, it gets worse. Any idea what that may be about?

Debugging react components in VS Code is a little weird, it would be nice to see an example

I just wanted to clarify this: `debugger` statements that were mentioned in the lesson are basically breakpoints, correct?

I also think I could benefit from seeing the debugger in React in an example, possibly with the Solar Farm React client.

I was wondering if we could see go over how to work over multiple components for solar farm so we had an example to go off of for the weekend assessment.







## Optional

_TODO_ show retrieving the list of materials from the API






## Misc

### Built-In Data Types in JS

_In Java..._

* int... Integer
* long... Long
* float... Float
* double... Double
* boolean... Boolean (wrap the primitive type value)
* ...

* ArrayList<Integer>
* List<Integer>
* ...

_In JavaScript..._

* number... Number
* boolean... Boolean
* ...

```js
const n = new Number();
```
