-- Inserting test data into the users table
INSERT INTO users (username, email, password_hash, first_name, last_name, role, created_at)
VALUES
    ('john_doe', 'john.doe@example.com', 'hashed_password_1', 'John', 'Doe', 'USER', CURRENT_TIMESTAMP),
    ('jane_doe', 'jane.doe@example.com', 'hashed_password_2', 'Jane', 'Doe', 'USER', CURRENT_TIMESTAMP),
    ('admin', 'admin@example.com', 'hashed_password_3', 'Admin', 'User', 'ADMIN', CURRENT_TIMESTAMP);

-- Inserting test data into the properties table
INSERT INTO properties (owner_id, title, description, address, city, state, country, price_per_night, max_guests, created_at, updated_at)
VALUES
    (1, 'Cozy Apartment', 'A cozy apartment in the heart of the city.', '123 Main St', 'New York', 'NY', 'USA', 150.00, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'Luxury Villa', 'A luxurious villa with stunning sea views.', '456 Ocean Drive', 'Miami', 'FL', 'USA', 500.00, 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Inserting test data into the bookings table
INSERT INTO bookings (property_id, user_id, check_in_date, check_out_date, total_price, status, created_at)
VALUES
    (1, 2, '2024-09-01', '2024-09-07', 900.00, 'CONFIRMED', CURRENT_TIMESTAMP),
    (2, 1, '2024-10-10', '2024-10-15', 2500.00, 'PENDING', CURRENT_TIMESTAMP);

-- Inserting test data into the property_images table
INSERT INTO property_images (property_id, image_url, created_at)
VALUES
    (1, 'https://example.com/images/property1/img1.jpg', CURRENT_TIMESTAMP),
    (1, 'https://example.com/images/property1/img2.jpg', CURRENT_TIMESTAMP),
    (2, 'https://example.com/images/property2/img1.jpg', CURRENT_TIMESTAMP);
