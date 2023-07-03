function addNewCategory() {
    //lay du lieu
    let name = $('#name').val();

    let newCategory = {
        name: name
    };
    // goi ajax
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(newCategory),
        //tên API
        url: "http://localhost:8080/api/categories",
        //xử lý khi thành công
        success: showAll
    });
    $('#myModal').modal('hide');
}


function getCategory(category) {
    return `<tr><td>${category.name}</td>
<td><button type="button" class="btn btn-info" data-toggle="modal" data-target="#myUpdateModal" 
onclick="showUpdate(${category.id})">Update</button></td>
</tr>`
}

function showAll() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/categories",
        success: function (data) {
            let content = '<table border="1" style="margin: auto;width: 700px;text-align: center"><tr><td>Name</td>' +
                '<td colspan="2">Action</td></tr>';
            for (let i = 0; i < data.length; i++) {
                content += getCategory(data[i]);
            }
            content += `</table>`
            document.getElementById("categoryList").innerHTML = content;
        }
    });
}


let updateId;

function showUpdate(id) {
    $.ajax({
        url: 'http://localhost:8080/api/categories/' + id,
        type: 'GET',
        success: function (response) {
            updateId = response.id;
            document.getElementById("nameUpdate").value = response.name;
        }
    });
}

function updateCategory() {
    let nameUpdate = document.getElementById("nameUpdate").value;

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: 'http://localhost:8080/api/categories/' + updateId,
        type: 'PUT',
        data: JSON.stringify({name: nameUpdate}),

        success: showAll
    })
    $('#myUpdateModal').modal('hide');
}






