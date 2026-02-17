MERGE INTO greeting (id, greeting_text, promotion_link)
    KEY (id)
    VALUES (1, 'Hello this is a greeting text', '/super_promotion');


MERGE INTO product_promotion (id, name, promotion_text, teaser_image_url)
    KEY (id)
    VALUES (
    1,
    'Spring Sale 2026',
    'Save up to 30% on selected items during our Spring Sale!',
    'https://cdn.example.com/images/promotions/spring-sale-2026.jpg'
    );

MERGE INTO products (id, promotion_id, name, price, image_url)
    KEY (id)
    VALUES (1, 1, 'Wireless Headphones', 89.99, 'https://cdn.example.com/images/products/headphones.jpg');

MERGE INTO products (id, promotion_id, name, price, image_url)
    KEY (id)
    VALUES (2, 1, 'Bluetooth Speaker', 59.50, 'https://cdn.example.com/images/products/speaker.jpg');

MERGE INTO products (id, promotion_id, name, price, image_url)
    KEY (id)
    VALUES (3, 1, 'Smart Watch', 149.00, 'https://cdn.example.com/images/products/smartwatch.jpg');

MERGE INTO products (id, promotion_id, name, price, image_url)
    KEY (id)
    VALUES (4, 1, 'Gaming Mouse', 39.95, 'https://cdn.example.com/images/products/mouse.jpg');