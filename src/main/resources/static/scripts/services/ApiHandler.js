app.service("apiHandler", function ($http, $cookies) {
    this.callPost = (url, data, onSuccess, onError, setToken) => {
        url = "/api/" + url;

        let request = {
            url: url,
            method: "POST",
            data: data
        };
        this.checkAndSetToken(request, setToken);
        $http(request).then((response) => {
            //when this work that you have Response.
            if (response != null && response.data != null) {
                const result = response.data;
                if (result.status == "SUCCESS") {
                    //call method onSuccess that selfWrite --> (response)=>{}
                    onSuccess(result);
                } else if (result.hasError) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: result.message,
                    })
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: "unknown error!!",
                    })
                }
            }
        }, (err) => {
            //error in internal layer or webServer error.
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: "Exception on server!!",
            })
            onError(err);
        });
    }

    this.callGet = (url, onSuccess, onError, setToken) => {
        url = "/api/" + url;
        let request = {
            url: url,
            method: "GET"
        };
        this.checkAndSetToken(request, setToken);
        $http(request).then((response) => {
            //when this work that you have Response.
            if (response != null && response.data != null) {
                const result = response.data;
                if (result.status == "SUCCESS") {
                    //call method onSuccess that selfWrite --> (response)=>{}
                    onSuccess(result);
                } else if (result.hasError) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: result.message,
                    })
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: "unknown error!!",
                    })
                }
            }
        }, (err) => {
            if (err.status === 417) {
                $cookies.remove("userToken");
                location.href = "/login";
                return;
            }
            //error in internal layer or webServer error.
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: "Exception on server!!",
            })
            onError(err);
        });
    }

    this.callPut = (url, data, onSuccess, onError, setToken) => {
        url = "/api/" + url;
        let request = {
            url: url,
            method: "PUT",
            data: data
        }
        this.checkAndSetToken(request, setToken);
        $http(request).then((response) => {
            //when this work that you have Response.
            if (response != null && response.data != null) {
                const result = response.data;
                if (result.status == "SUCCESS") {
                    onSuccess(result);
                } else if (result.hasError) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: result.message,
                    })
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: "unknown error!!",
                    })
                }
            }
        }, (err) => {
            //error in internal layer or webServer error.
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: "Exception on server!!",
            })
            onError(err);
        });
    }

    this.callDelete = (url, onSuccess, onError, setToken) => {
        url = "/api/" + url;
        let request = {
            url: url,
            method: "DELETE",
        }
        this.checkAndSetToken(request, setToken);
        $http(request).then((response) => {
            //when this work that you have Response.
            if (response != null && response.data != null) {
                const result = response.data;
                if (result.status === "SUCCESS") {
                    onSuccess(result);
                } else if (result.hasError) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: result.message,
                    })
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: "unknown error!!",
                    })
                }
            }
        }, (err) => {
            //error in internal layer or webServer error.
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: "Exception on server!!",
            })
            onError(err);
        });
    }

    this.checkAndSetToken = (request, setToken) => {
        //setToken is boolean.
        if (setToken) {
            let token = $cookies.get("userToken");
            request.headers = {
                "Authorization": "Bearer " + token
            };
        }
    }
});