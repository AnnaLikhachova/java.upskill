<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>All Products page</title>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light mt-5 d-flex flex-row justify-content-around">
        <h3 class="nav-item p-2"><a href="main" class="nav-link text-warning">Main Page</a></h3>
        <h3 class="nav-item p-2"><a href="allproducts" class="nav-link text-dark">All Products</a></h3>
        <h3 class="nav-item p-2"><a href="addproduct" class="nav-link text-warning">Add Product</a></h3>
    </nav>
</div>
<div class="container mt-5">
    <div class="container-fluid p-0 mt-2">
        <div class="row">
            <div class="col-xl-12">
                <div class="card">
                    <div class="card-header pb-0">
                        <h5 class="card-title m-2">All products</h5>
                    </div>
                    <div class="card-body">
                        <table class="table table-striped" style="width:100%">
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Date</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                            </thead>
                            <tbody>
                               <#list products as product>
                               <tr>
                                   <td>${product.id}</td>
                                   <td>${product.name}</td>
                                   <td>${product.price}</td>
                                   <td>${product.date}</td>
                                   <td>
                                    <span class="badge bg-danger">
                                        <form class="mb-0" action="/allproducts" method="POST">
                                            <input hidden="text" value=${product.id} name="id"/>
                                            <input class="border border-danger bg-danger" type="submit" value="DELETE""/>
                                        </form>
                                    </span>
                                   </td>
                                   <td>
                                       <span class="badge bg-warning p-1"><a href="updateproduct?id=${product.id}"
                                                                             class="text-decoration-none text-reset m-1">UPDATE</a></span>
                                   </td>
                               </tr>
                               </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>