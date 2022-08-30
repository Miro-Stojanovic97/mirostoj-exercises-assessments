import {useState, useEffect} from "react";
import Movie from "./Movie";

function Movies() {

    const [currentMovies, setCurrentMovies] = useState([]);
    // let currentMovies = [];
    // const setCurrentMovies = (moviesData) => {
    //     currentMovies = moviesData;
    // }


    // const [clicked, setClicked] = useState(false);


    useEffect(() => {
        setCurrentMovies([
            {
                name: "Bullet Train",
                releaseDate: "August 5",
                imgUrl: "https://resizing.flixster.com/wc9Zy3NQxzYLKPKQiIk-Oxx7R9s=/400x600/v2/https://resizing.flixster.com/0Wuqfdlk2B3QUnrB7wPlQ7c47AI=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzLzI3OTIwZDU1LTEwNmQtNGYwZS1iNjhlLTIwNmU0MjdiYmY2MS5qcGc="
            },
            {
                name: "Nope",
                releaseDate: "July 22",
                imgUrl: "https://resizing.flixster.com/M3XP9CwLUPPyYclPb7ZropFn5y0=/400x600/v2/https://resizing.flixster.com/6EeYrywKueOFue9uRSQqLxfGKI0=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzLzA0NzZhZTk0LTI3NjctNGZiNi04Yjg4LWRiN2RkNTJiYWZlNy5qcGc="
            },
            {
                name: "Thor: Love and Thunder",
                releaseDate: "July 8",
                imgUrl: "https://resizing.flixster.com/ZSYVab-WDz5Ff22esQk16rO39NU=/400x600/v2/https://resizing.flixster.com/zs8TFWbPfSsndNJWCpNiILwzf3o=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzL2JiYzJkMmE5LTllZDUtNDQ0Ny1hYmUxLTBmMzk1MDQ4M2NkNC5qcGc="
            }
        ]);
    }, [])

    const movieFactory = () => {
        return currentMovies.map(movie => <Movie key={movie.name} movieProp={movie} />)
    }

    // useEffect(() => {
    //     setCurrentMovies([
    //         ...currentMovies, 
    //         {
    //             name: "Top Gun: Maverick",
    //             releaseDate: "May 27",
    //             imgUrl: "https://resizing.flixster.com/NCTU_xPUO76KLEnKdd7oWGB3MCw=/400x600/v2/https://resizing.flixster.com/TIM4kfHTVZrfpF0tYt9LIU69A5s=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzLzU1OWIwMWQwLWYyZDItNDk4Yi04MDIxLWI3OTJlNDI1NjA3NS5qcGc="
    //         }
    //     ])
    // }, [clicked]);

    const addAMovie = () => {
        setCurrentMovies([
            ...currentMovies, 
            {
                name: "DC League of Super Pets",
                releaseDate: "July 29",
                imgUrl: "https://resizing.flixster.com/xtMSLzWLLUcGPST_z2Jl3AbQmXs=/400x600/v2/https://resizing.flixster.com/QVLNsMuiMjENvBgmsfoV0rTV-Wk=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzLzQ3NzMyMDU0LWNlZWUtNDJkYy1iMmNmLTMzNjY1MTEyY2E2ZS5qcGc="
            }
        ])
    }

    return (
        <>
            <div className="row">
                {movieFactory()}
            </div>
            <div className="row">
                <div className="col-2">
                    <button className="btn btn-primary mt-3 btn-sm" onClick={addAMovie}>Add Another Movie</button>
                </div>
                {/* 
                <div className="col-2">
                    <button className="btn btn-success" onClick={() => setClicked(!clicked)}>Add Another Movie</button> 
                </div>    
                */}
            </div>
        </>
    )
}

export default Movies;