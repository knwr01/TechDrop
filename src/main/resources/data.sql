DELETE FROM products;

INSERT INTO products
(product_name, description, price, stock_quantity, brand, category, created_at)
VALUES
('iPhone 16', 'Apple smartphone', 1499.99, 20, 'Apple', 'Smartphones', NOW()),
('Galaxy S25', 'Samsung smartphone', 1399.99, 15, 'Samsung', 'Smartphones', NOW()),
('MacBook Air M3', 'Apple laptop', 1899.99, 10, 'Apple', 'Laptops', NOW()),
('Dell XPS 15', 'Dell laptop', 2199.99, 8, 'Dell', 'Laptops', NOW()),
('Sony WH-1000XM6', 'Wireless noise cancelling headphones', 549.99, 30, 'Sony', 'Audio', NOW());

DELETE FROM users;

-- passwords: admin/Admin@123, staff/Staff@123
INSERT INTO users
(username, email, password, role)
VALUES
('admin', 'admin@techdrop.com', '$2a$10$MHIQVCGGbKj40Ldiu2cRyuDqMV9vWkD.cmYsKWsYGPNruOBywbFNG', 'ADMIN'),
('staff', 'staff@techdrop.com', '$2a$10$LHDFRl953FAyb4B.Fq/rSuW2VmPXoiALlto5nfeZM/YVdvzWjE2fG', 'STAFF');