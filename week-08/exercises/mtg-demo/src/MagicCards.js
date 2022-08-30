import MagicCard from "./MagicCard";

function MagicCards({cardsProp}) {
    // let cardsProp = props.cardsProp

    const cardFactory = () => {
        return cardsProp.map(card => {
            return (
                <MagicCard cardProp={card} />
            )
        })
    }

    return (
        <>
            <h2>List of Cards:</h2>
            {cardFactory()}
        </>
    )
}

export default MagicCards;