INSERT INTO role (role, description) VALUES ('ADMIN', 'Administrator Privileges');
INSERT INTO role (role, description) VALUES ('OWNER', 'Owner Privileges');
INSERT INTO role (role, description) VALUES ('CUSTOMER', 'Customer Privileges');

INSERT INTO users (name, email, password) VALUES ('admin', 'admin@admin.com', 'admin');
INSERT INTO users (name, email, password) VALUES ('owner', 'owner@owner.com', 'owner');
INSERT INTO users (name, email, password) VALUES ('customer', 'customer@customer.com', 'customer');
INSERT INTO users (name, email, password) VALUES ('blacklisted', 'blacklisted@blacklisted.com', 'owner');

-- admin, ADMIN
INSERT INTO role_users (roles_role, users_id) VALUES ('ADMIN', 1);
-- owner, OWNER
INSERT INTO role_users (roles_role, users_id) VALUES ('OWNER', 2);
-- customer, CUSTOMER
INSERT INTO role_users (roles_role, users_id) VALUES ('CUSTOMER', 3);
-- blacklisted, OWNER
INSERT INTO role_users (roles_role, users_id) VALUES ('OWNER', 2);

-- Taken from https://gist.github.com/dankohn/09e5446feb4a8faea24f
INSERT INTO address (latitude, longitude, street, city, state, zip) VALUES (-149.8935557, 61.21759217, '603 West Street_601 West 5th Avenue', 'Anchorage', 'AK', '99501');
INSERT INTO address (latitude, longitude, street, city, state, zip) VALUES (-111.9140702, 33.34918409, 'Safeway-Tempe #1535_1515 E Elliott Rd', 'Tempe', 'AZ', '85284');
INSERT INTO address (latitude, longitude, street, city, state, zip) VALUES (-121.7860158, 37.96796292, 'Lone Tree & Slatten Ranch - Antioch_5779 Lone Tree Way', 'Antioch', 'CA', '94531');
INSERT INTO address (latitude, longitude, street, city, state, zip) VALUES (-84.05359202, 39.7670933,  'Mall at Fairfield Commons_2727 Fairfield Commons', 'Beavercreek', 'OH', '45431');
INSERT INTO address (latitude, longitude, street, city, state, zip) VALUES (-73.95426939, 40.78722936, '96th & Madison_1378', 'Madison', 'NY', '10128');
INSERT INTO address (latitude, longitude, street, city, state, zip) VALUES (-87.62449686, 41.88599507, 'Illinois Center_225 N Michigan Ave', 'Chicago', 'IL', '60601');
INSERT INTO address (latitude, longitude, street, city, state, zip) VALUES (-108.5762797, 45.76378374, 'Billings-Rimrock Mall_316 South 24th Street West_Billings', 'Montana', 'MT', '59102');

INSERT INTO person (dtype, black_listed, email, first_name, last_name, address_id, user_id, approved, approved_by_id) VALUES  ('Administrator', false, 'admin@admin.com', 'John', 'Doe', 1, 1, true, 1);
INSERT INTO person (dtype, black_listed, email, first_name, last_name, address_id, user_id, approved, approved_by_id) VALUES  ('Owner', false, 'owner@owner.com', 'Keith', 'Junior', 2, 2, true, 1);
INSERT INTO person (dtype, black_listed, email, first_name, last_name, address_id, user_id, approved, approved_by_id) VALUES  ('Customer', false, 'customer@customer.com', 'Ben', 'Alpha', 3, 3, true, 1);
INSERT INTO person (dtype, black_listed, email, first_name, last_name, address_id, user_id, black_listed_by_id, approved) VALUES  ('Owner', false, 'blacklisted@blacklisted.com', 'Wide', 'Dan', 4, 4, 1, false);

INSERT INTO property (no_of_bathrooms, no_of_bedrooms, price, area, plot_size, status, address_id, owner_id) VALUES (1, 2, 265000, 1250, 6500.5, 'PENDING', 5, 2);
INSERT INTO property (no_of_bathrooms, no_of_bedrooms, price, area, plot_size, status, address_id, owner_id) VALUES (3, 4, 755000, 2550, 10500.5, 'AVAILABLE', 6, 2);
INSERT INTO property (no_of_bathrooms, no_of_bedrooms, price, area, plot_size, status, address_id, owner_id) VALUES (1, 1, 125800, 750, 2500.0, 'AVAILABLE', 7, 4);

INSERT INTO message (date, time, message, property_id, recipient_id, reply_to_id, sender_id) VALUES (TO_DATE('04/10/2023', 'MM/DD/YYYY'), '11:15:00', 'Hello there!!!, Can you please send me when I can view this property?', 1, 2, null, 3);
INSERT INTO message (date, time, message, property_id, recipient_id, reply_to_id, sender_id) VALUES (TO_DATE('04/10/2023', 'MM/DD/YYYY'), '11:30:00', 'Hi, Thank you for the inquiry. I am available this week end. Please let me know when you will be arriving. There are couple of more people who will be coming for a viewing.', 1, 2, 1, 3);
INSERT INTO message (date, time, message, property_id, recipient_id, reply_to_id, sender_id) VALUES (TO_DATE('04/10/2023', 'MM/DD/YYYY'), '11:40:00', 'Ok, I will update you on Friday.', 1, 2, 2, 3);

INSERT INTO offer (date, time, offer_amount, customer_id, property_id, status) VALUES (TO_DATE('04/11/2023', 'MM/DD/YYYY'), '10:00:00', 225000, 3, 1, 'EVALUATING');