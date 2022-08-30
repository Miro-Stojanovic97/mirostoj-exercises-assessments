export async function authenticate(username, password) {
  const init = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      username,
      password
    })
  };
  const response = await fetch('http://localhost:8080/authenticate', init);
  if (response === 200) {
    const result = await response.json();
    // TODO: we must handle the result somehow
  }
}