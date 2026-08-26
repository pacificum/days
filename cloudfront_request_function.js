function handler(event) {
    var request = event.request;
    var uri = request.uri;

    if (request.uri === '/') {
        return {
            statusCode: 301,
            statusDescription: 'Moved Permanently',
            headers: {
                location: {
                    value: '/days/'
                }
            }
        };
    } else if (uri.endsWith('/')) {
        request.uri += 'index.html';
    }
    return request;
}