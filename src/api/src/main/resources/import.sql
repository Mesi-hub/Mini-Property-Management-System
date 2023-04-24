INSERT INTO role (role, description) VALUES ('ADMIN', 'Administrator Privileges');
INSERT INTO role (role, description) VALUES ('OWNER', 'Owner Privileges');
INSERT INTO role (role, description) VALUES ('CUSTOMER', 'Customer Privileges');

INSERT INTO users (name, email, password) VALUES ('admin', 'admin@admin.com', 'admin');
INSERT INTO users (name, email, password) VALUES ('owner', 'owner@owner.com', 'owner');
INSERT INTO users (name, email, password) VALUES ('customer', 'customer@customer.com', 'customer');
INSERT INTO users (name, email, password) VALUES ('blacklisted', 'blacklisted@blacklisted.com', 'owner');

-- admin, ADMIN
INSERT INTO role_users (roles_role, users_id) VALUES (1, 1);
-- owner, OWNER
INSERT INTO role_users (roles_role, users_id) VALUES (2, 2);
-- customer, CUSTOMER
INSERT INTO role_users (roles_role, users_id) VALUES (3, 3);
-- blacklisted, OWNER
INSERT INTO role_users (roles_role, users_id) VALUES (4, 2);

-- Taken from https://gist.github.com/dankohn/09e5446feb4a8faea24f
INSERT INTO address (latitude, longitude, street, city, state, zip) VALUES (-149.8935557, 61.21759217, '603 West Street_601 West 5th Avenue', 'Anchorage', 'AK', '99501');
INSERT INTO address (latitude, longitude, street, city, state, zip) VALUES (-111.9140702, 33.34918409, 'Safeway-Tempe #1535_1515 E Elliott Rd', 'Tempe', 'AZ', '85284');
INSERT INTO address (latitude, longitude, street, city, state, zip) VALUES (-121.7860158, 37.96796292, 'Lone Tree & Slatten Ranch - Antioch_5779 Lone Tree Way', 'Antioch', 'CA', '94531');
INSERT INTO address (latitude, longitude, street, city, state, zip) VALUES (-84.05359202, 39.7670933,  'Mall at Fairfield Commons_2727 Fairfield Commons', 'Beavercreek', 'OH', '45431');
INSERT INTO address (latitude, longitude, street, city, state, zip) VALUES (-73.95426939, 40.78722936, '96th & Madison_1378', 'Madison', 'NY', '10128');
INSERT INTO address (latitude, longitude, street, city, state, zip) VALUES (-87.62449686, 41.88599507, 'Illinois Center_225 N Michigan Ave', 'Chicago', 'IL', '60601');
INSERT INTO address (latitude, longitude, street, city, state, zip) VALUES (-108.5762797, 45.76378374, 'Billings-Rimrock Mall_316 South 24th Street West_Billings', 'Montana', 'MT', '59102');

