function Message({messages, setMessages}) {

    const clearAlert = () => {
        setMessages([]);
        const messageAlert = document.getElementById('messages');
        messageAlert.setAttribute('class', 'alert alert-secondary alert-dismissible fade');
    }

    const allMessages = () => {
        return messages.map(m => <p className="mb-0" key={m}>{m}</p>)
    }

    return (
        <div className="alert alert-secondary alert-dismissible fade" role="alert" id="messages">
            {allMessages()}
            <button onClick={clearAlert} type="button" className="btn-close" aria-label="Close"></button>
        </div>
    )
}

export default Message;