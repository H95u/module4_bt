let pageIndex;

function getProduct(product) {
    const manufactureDate = new Date(product.manufactureDate);
    const formattedManufactureDate = `${manufactureDate.getDate()}/${manufactureDate.getMonth() + 1}/${manufactureDate.getFullYear()}`;

    const expireDate = new Date(product.expireDate);
    const formattedExpireDate = `${expireDate.getDate()}/${expireDate.getMonth() + 1}/${expireDate.getFullYear()}`;

    return `
        <div class="col-lg-4">
            <div class="card">
                <button onclick="viewProduct(${product.id})">
                <img src="/file/${product.img}" class="card-img-top" alt="Product Image">
                </button>
                <div class="card-body">
                    <h3 class="card-title">${product.name}</h3>
                    <p class="card-text">Price: ${product.price}</p>
                    <p class="card-text">Quantity: ${product.quantity}</p>
                    <p class="card-text">Manufacture Date: ${formattedManufactureDate}</p>
                    <p class="card-text">Expire Date: ${formattedExpireDate}</p>
                </div>
            </div>
        </div>
    `;
}

function showAll(page) {
    pageIndex = page;
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/api/products?page=${pageIndex}`,
        success: function (data) {
            let content = `<div class="container" style="text-align: center"><div class="row">`;

            for (let i = 0; i < data.content.length; i++) {
                content += getProduct(data.content[i]);
            }
            content += `</div>`;
            if (!data.first) {
                content += `<button onclick="prevPage()">Prev</button>`;
            }
            content += `${data.number + 1}|${data.totalPages}`;
            if (!data.last) {
                content += `<button onclick="nextPage()">Next</button>`;
            }
            content += `</div>`;

            document.getElementById("productList").innerHTML = content;
        }
    });
}

function prevPage() {
    pageIndex--;
    showAll(pageIndex);
}

function nextPage() {
    pageIndex++;
    showAll(pageIndex);
}


function addNewProduct() {
    //lay du lieu
    let formData = new FormData();
    let file = document.getElementById("img").files[0];
    let filePath = document.getElementById("img").value;
    let img = filePath.split("\\")
    let name = $('#name').val();
    let price = $('#price').val();
    let quantity = $('#quantity').val();
    let mDate = $('#mDate').val();
    let eDate = $('#eDate').val();
    let categoryId = $('#categoryId').val();
    let newProduct = {
        name: name,
        img: img[img.length - 1],
        price: price,
        quantity: quantity,
        manufactureDate: mDate,
        expireDate: eDate,
        category: {
            id: categoryId
        }
    };
    formData.append("file", file);
    formData.append("data", JSON.stringify(newProduct));
    $.ajax({
        url: "http://localhost:8080/api/products",
        type: "POST",
        data: formData,
        contentType: false,
        processData: false,
        success: console.log("success")
    });
    $('#myModal').modal('hide');
}

function getCategoryOption(category) {
    return `<option value="${category.id}">${category.name}</option>`
}

function getAllSelectCategory() {

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/categories",
        success: function (data) {
            let content = `<select id="categoryId">`
            for (let i = 0; i < data.length; i++) {
                content += getCategoryOption(data[i]);
            }
            content += `</select>`;
            document.getElementById("selectCategory").innerHTML = content;
        }
    });

}

function viewProduct(id) {
    window.location.href = "view.html"
    $.ajax({
        url: 'http://localhost:8080/api/products/' + id,
        type: 'GET',
        success: function (response) {

        }
    });
}


function deleteStudent(id) {
    if (confirm("Sure ?")) {
        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/api/products/" + id,
            success: showAll
        });
    }
}

let updateId;

function showUpdate(id) {
    updateId = id;
    $.ajax({
        url: 'http://localhost:8080/api/products/' + id,
        type: 'GET',
        success: function (response) {
            document.getElementById("nameUpdate").value = response.name;
            document.getElementById("pointUpdate").value = response.point;
            document.getElementById("ageUpdate").value = response.age;
        }
    });
}

function updateStudent() {
    let nameUpdate = document.getElementById("nameUpdate").value;
    let pointUpdate = document.getElementById("pointUpdate").value;
    let ageUpdate = document.getElementById("ageUpdate").value;
    let classId = $('select:last').val();
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: 'http://localhost:8080/api/students/' + updateId,
        type: 'PUT',
        data: JSON.stringify({
            name: nameUpdate,
            point: pointUpdate,
            age: ageUpdate,
            classes: {
                id: classId
            }
        }),
        success: showAll
    })
    $('#myUpdateModal').modal('hide');
}
