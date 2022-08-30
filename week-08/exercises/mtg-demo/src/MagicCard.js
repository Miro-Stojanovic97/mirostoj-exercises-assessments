function MagicCard({cardProp}) {
    return (
        <div key={cardProp.multiverseid} id={cardProp.multiverseid} className="magic-card">
            <h4>{cardProp.name}</h4>
            <img src={cardProp.imageUrl} alt={cardProp.name} />
        </div>
    )
}

export default MagicCard;