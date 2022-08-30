In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

---------------------------------------------

Creating new (child) Components
    
    App
        Movies
             Movie

Components can have custom props (properties) we define, and pass to child components

Often, factory functions are used to generate JSX for repetitive data, such as a card for every movie in our dataset

useState

    Like setting a global variable/temporary memory for a component
    setter/getter, i.e. const [state, setState] = useState();

useEffect

    Determines when defined functions fire
    Will always fire on first load
        Can set second argument to a state variable to also trigger re-render on state change

