app.service("mainApiHandler", function ($http) {
    this.callPost = (url, data, onSuccess, onError) => {
        url = "/api/" + url;

        let request = {
            url: url,
            method: "POST",
            data: data
        };
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
    };

    this.callGet = (url, onSuccess, onError) => {
        url = "/api/" + url;
        let request = {
            url: url,
            method: "GET"
        };
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
    };

    this.callPut = (url, data, onSuccess, onError) => {
        url = "/api/" + url;
        let request = {
            url: url,
            method: "PUT",
            data: data
        }
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
    };

    this.callDelete = (url, onSuccess, onError) => {
        url = "/api/" + url;
        let request = {
            url: url,
            method: "DELETE",
        }
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
            });
            onError(err);
        });
    };
});