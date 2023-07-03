function test() {
    let formData = new FormData();
    let file = document.getElementById("test").files[0];
    let filePath = document.getElementById("test").value;
    let filename = filePath.split("\\")
    formData.append("file", file)
    let newProduct = {
        img: filename[filename.length - 1]
    }
    formData.append("data", JSON.stringify(newProduct));
    $.ajax({
        url: "http://localhost:8080/api/products",
        type: "POST",
        data: formData,
        contentType: false,
        processData: false,
        success: showAll
    });
}

function showAll() {
    $.ajax({
        url: "http://localhost:8080/api/products",
        type: "GET",
        success: function (data) {
            let content = "";
            for (let i = 0; i < data.length; i++) {
                content += `<img width="50" height="50" src="/file/${data[i].img}"><hr>`
            }
            document.getElementById("img").innerHTML = content
        }
    });
}

