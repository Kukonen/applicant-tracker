type methodType = 'GET' | 'POST' | 'PUT' | 'DELETE'

const fetchError = (response: Response) => {
    if (!response.ok) {
        throw Error(response.statusText);
    }
    return response.json();
}
export const request = async (
    URL: string,
    method: methodType = 'GET',
    body?: Object | string
) => {
    return fetch(URL, {
        method: method,
        body: JSON.stringify(body)
    })
        .then(response => {
            return fetchError(response);
        })
        .then(data => {
            return data;
        })
        .catch(error => {
            console.log("ERROR! ", error);
            return error;
        });
}