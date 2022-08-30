function Movie({movieProp}) {
    return (
        <div className="col">
            <div className="card">
                <img src={movieProp.imgUrl} className="card-img-top" alt={movieProp.name} />
                <div className="card-body">
                    <h5 className="card-title">{movieProp.name}</h5>
                    <p className="card-text">{movieProp.releaseDate}</p>
                </div>
            </div>
        </div>
    )
}

export default Movie;