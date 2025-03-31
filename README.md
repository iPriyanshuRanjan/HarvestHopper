### **Product Service API**

The `ProductService` provides CRUD operations and search functionality for managing products.

#### **Methods**

1. **Create a Product**
    - `Product createProduct(Product product)`

2. **Create Multiple Products**
    - `List<Product> createProducts(List<Product> products)`

3. **Update Product by ID**
    - `Product updateProductById(long id, Product product)`

4. **Delete Product by ID**
    - `void deleteProductById(long id)`

5. **Get Product by ID**
    - `Product getProductById(long id)`

6. **Get All Products**
    - `List<Product> getAllProducts()`

7. **Search Products by Name**
    - `List<Product> searchProductByName(String keyword)`
---

## **🔗 API Endpoints (for reference)**

| **Functionality**        | **Method** | **Endpoint**                    |
|-------------------------|-----------|--------------------------------|
| Create a product        | `POST`    | `/products`                    |
| Create multiple products | `POST`    | `/products/batch`              |
| Update a product        | `PUT`     | `/products/{id}`               |
| Delete a product        | `DELETE`  | `/products/{id}`               |
| Get product by ID       | `GET`     | `/products/{id}`               |
| Get all products        | `GET`     | `/products`                    |
| Search products by name | `GET`     | `/products/search?keyword=xyz` |
---

### **🚀 Author: HarvestHopper Team**
_Last Updated: March 2025_
