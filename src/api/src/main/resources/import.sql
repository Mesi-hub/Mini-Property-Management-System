INSERT INTO role (role, description) VALUES ('ADMIN', 'Administrator Privileges');
INSERT INTO role (role, description) VALUES ('OWNER', 'Owner Privileges');
INSERT INTO role (role, description) VALUES ('CUSTOMER', 'Customer Privileges');

INSERT INTO users (name, email, password) VALUES ('admin', 'admin@admin.com', 'admin');
INSERT INTO users (name, email, password) VALUES ('owner', 'owner@owner.com', 'owner');
INSERT INTO users (name, email, password) VALUES ('customer', 'customer@customer.com', 'customer');
INSERT INTO users (name, email, password) VALUES ('blacklisted', 'blacklisted@blacklisted.com', 'owner');

-- admin -> ADMIN, OWNER, CUSTOMER
INSERT INTO role_users (roles_role, users_id) VALUES ('ADMIN', 1);
INSERT INTO role_users (roles_role, users_id) VALUES ('OWNER', 1);
INSERT INTO role_users (roles_role, users_id) VALUES ('CUSTOMER', 1);
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

INSERT INTO person (dtype, black_listed, email, first_name, last_name, address_id, user_id, approved) VALUES  ('Administrator', false, 'admin@admin.com', 'John', 'Doe', 1, 1, false);
INSERT INTO person (dtype, black_listed, email, first_name, last_name, address_id, user_id, approved, approved_by_id) VALUES  ('Owner', false, 'owner@owner.com', 'Keith', 'Junior', 2, 2, true, 1);
INSERT INTO person (dtype, black_listed, email, first_name, last_name, address_id, user_id, approved) VALUES  ('Customer', false, 'customer@customer.com', 'Ben', 'Alpha', 3, 3, false);
INSERT INTO person (dtype, black_listed, email, first_name, last_name, address_id, user_id, black_listed_by_id, approved) VALUES  ('Owner', true, 'blacklisted@blacklisted.com', 'Wide', 'Dan', 4, 4, 1, false);

INSERT INTO property (no_of_bathrooms, no_of_bedrooms, price, area, plot_size, status, description, title, address_id, owner_id) VALUES (1, 2, 265000, 1250, 6500.5, 'PENDING', 'Leave your worries behind when you come home to Vail Quarters. Our pet-friendly apartments and townhomes in North Dallas near Galleria offer a sense of escape from the daily hustle. This feeling of tranquility and relaxation starts when you glimpse the verdant landscaping in our garden-style community and is enhanced by resort-style amenities and a variety of upscale features and floor plans designed to complement your lifestyle.', 'A masterpiece. With stunning views.', 5, 2);
INSERT INTO property (no_of_bathrooms, no_of_bedrooms, price, area, plot_size, status, description, title, address_id, owner_id) VALUES (3, 4, 755000, 2550, 10500.5, 'AVAILABLE', 'We are now accepting in-person and self-guided tours via scheduled appointments only. Our virtual tours are also available. Please schedule yours today.Enjoy the tranquility of an established community with mature trees and fully landscaped grounds. Tiburon is located in the prestigious Prestonwood / Galleria area of North Dallas, so you''re just minutes away from fine dining, shopping, entertainment and major employers. Consider that along with our location near I635, the North Dallas Tollway, and the Bush Turnpike, and you have found the perfect location to call home. Our well-designed apartment homes offer a variety of exceptional large floor plans with spacious living areas, including an abundance of huge walk-in closets. -- The impressive array of interior amenities include wood burning fireplaces, garden windows, microwave ovens in select units, frost free refrigerators with icemakers, large gourmet kitchens and much more! The community features three sparkling swimming pools, resident fitness facility, on-site clothes care facilities, and curbside refuse pickup....just a few reasons to call Tiburon home!', 'The luxury at its best',6, 2);
INSERT INTO property (no_of_bathrooms, no_of_bedrooms, price, area, plot_size, status, description, title, address_id, owner_id) VALUES (1, 1, 125800, 750, 2500.0, 'AVAILABLE', 'If you are searching for a relaxing and inviting space, you''re in luck! Every floorplan offers spacious living areas, wood-inspired flooring, a private patio or balcony, washer/dryer connections, a cozy fireplace in select units, generous storage space, as well as a fully-equipped kitchen with black or stainless steel appliances, brushed nickel hardware, and a modern backsplash!', 'Small family heaven.',7, 4);

INSERT INTO message (date, time, message, property_id, recipient_id, reply_to_id, sender_id) VALUES (TO_DATE('04/10/2023', 'MM/DD/YYYY'), '11:15:00', 'Hello there!!!, Can you please send me when I can view this property?', 1, 2, null, 3);
INSERT INTO message (date, time, message, property_id, recipient_id, reply_to_id, sender_id) VALUES (TO_DATE('04/10/2023', 'MM/DD/YYYY'), '11:30:00', 'Hi, Thank you for the inquiry. I am available this week end. Please let me know when you will be arriving. There are couple of more people who will be coming for a viewing.', 2, 3, 1, 2);
INSERT INTO message (date, time, message, property_id, recipient_id, reply_to_id, sender_id) VALUES (TO_DATE('04/10/2023', 'MM/DD/YYYY'), '11:40:00', 'Ok, I will update you on Friday.', 1, 2, 2, 3);

INSERT INTO saved_property (date, time, customer_id, property_id) VALUES (TO_DATE('04/10/2023', 'MM/DD/YYYY'), '10:00:00', 3, 1);
INSERT INTO saved_property (date, time, customer_id, property_id) VALUES (TO_DATE('04/10/2023', 'MM/DD/YYYY'), '10:00:00', 3, 2);

INSERT INTO offer (date, time, offer_amount, customer_id, property_id, status) VALUES (TO_DATE('04/11/2023', 'MM/DD/YYYY'), '10:00:00', 225000, 3, 1, 'EVALUATING');