function Heading({greeting}) {
    // const greeting = props.greeting;
    return (
        <h2 className="greeting-text">
            {greeting ? greeting : "Hey buds 🥴"}
            {/* If THAT? DO_THIS :ELSE: DO_THIS */}
        </h2>
    );
}

export default Heading;