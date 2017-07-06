insert into LAB3_OBJECT_TYPES (OBJECT_TYPE_ID, NAME, parent_id, product, icon) values (1, 'brand', null, 0, 'fa fa-copyright');
insert into LAB3_OBJECT_TYPES (OBJECT_TYPE_ID, NAME, parent_id, product, icon) values (2, 'Phone', null, 1, 'fa fa-mobile');
insert into LAB3_OBJECT_TYPES (OBJECT_TYPE_ID, NAME, parent_id, product, icon) values (3, 'Headphones', 2, 1, 'fa fa-headphones');
insert into LAB3_OBJECT_TYPES (OBJECT_TYPE_ID, NAME, parent_id, product, icon) values (4, 'Charger',  2, 1, 'fa fa-plug');
insert into LAB3_OBJECT_TYPES (OBJECT_TYPE_ID, NAME, parent_id, product, icon) values (5, 'Battery', 2, 1, 'fa fa-bolt');
insert into LAB3_OBJECT_TYPES (OBJECT_TYPE_ID, NAME, parent_id, product, icon) values (6, 'user', null, 0, 'fa fa-user');
insert into LAB3_OBJECT_TYPES (OBJECT_TYPE_ID, NAME, parent_id, product, icon) values (7, 'cart', 6, 0, 'fa fa-shopping-cart');
insert into LAB3_OBJECT_TYPES (OBJECT_TYPE_ID, NAME, parent_id, product, icon) values (8, 'icons', null, 0, 'fa fa-user');
--attributes for brand
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (1, 'logo', 1, 0, 0, 1);
--attributes for phone
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (2, 'name', 2, 0, 0, 0);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (3, 'price', 2, 0, 0, 0);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (4, 'icon', 2, 0, 0, 1);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (5, 'icon2', 2, 0, 0, 1);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (6, 'icon3', 2, 0, 0, 1);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (7, 'OS', 2, 0, 0, 0);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (8, 'diagonal', 2, 0, 0, 0);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (9, 'rating', 2, 0, 1, 0);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (10, 'quantity', 2, 0, 0, 0);
--attributes for headphones
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (11, 'name', 3, 0, 0, 0);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (12, 'price', 3, 0, 0, 0);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (13, 'icon', 3, 0, 0, 1);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (14, 'icon2', 3, 0, 0, 1);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (15, 'icon3', 3, 0, 0, 1);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (16, 'microphone', 3, 0, 0, 0);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (17, 'rating', 3, 0, 1, 0);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (18, 'quantity', 3, 0, 0, 0);
--attributes for charger
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (19,  'name', 4, 0, 0, 0);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (20, 'price', 4, 0, 0, 0);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (21, 'cable length', 4, 0, 0, 0);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (22, 'rating', 4, 0, 0, 0);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (23, 'quantity', 4, 0, 0, 0);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (24, 'power', 4, 0, 0, 0);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (25, 'icon', 4, 0, 0, 1);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (26, 'icon2', 4, 0, 0, 1);
--attributes for battery
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (27, 'name', 5, 0, 0, 0);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (28, 'price', 5, 0, 0, 0);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (29, 'rating', 5, 0, 0, 0);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (30, 'quantity', 5, 0, 0, 0);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (31, 'capacity', 5, 0, 0, 0);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (32, 'icon', 5, 0, 0, 1);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (33, 'icon2', 5, 0, 0, 1);
--attributes for user
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (34, 'login', 6, 1, 0, 0);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (35, 'password', 6, 0, 0, 0);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (36, 'role', 6, 0, 0, 0);
--attribute for cart
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID, UNIQ, HID, ATTACH) values (37, 'item', 7, 0, 0, 0);
--adding brands
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (2, 'Samsung', 1, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (3, 'HTC', 1, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (4, 'Sony', 1, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (5, 'Apple', 1, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (6, 'LG', 1, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (7, 'Lenovo', 1, null);
--user ADMIN
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (1, 'admin', 6, null);

insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (1, 'admin', 34, 1);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (2, '$2a$11$BqW8JyfdjD65PgnrR7Q4LeLwYWG43U1LEjoVU7T67JC/UPV19B9hK', 35, 1);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (3, 'ADMIN', 36, 1);
--adding brands logos
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (4, '/resources/img/brand3.png', 1, 2);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (5, '/resources/img/brand5.png', 1, 3);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (6, '/resources/img/brand1.png', 1, 4);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (7, '/resources/img/brand4.png', 1, 5);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (8, '/resources/img/brand6.png', 1, 6);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (9, '/resources/img/brand2.png', 1, 7);
--adding phones
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (8, 'SAMSUNG SM-J700H GALAXY J7 DS BLACK', 2, 1);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (9, 'SAMSUNG A510F GALAXY A5', 2, 1);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (10, 'SAMSUNG SM-J700H GALAXY J7 DS WHITE', 2, 1);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (11, 'LENOVO K5 (A6020A40) DUAL SIM GREY', 2, 6);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (12, 'HTC DESIRE 630 DUAL SIM DARK GREY', 2, 2);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (13, 'APPLE IPHONE 7 32GB ROSE', 2, 4);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (14, 'APPLE IPHONE 6S 128GB GOLD', 2, 4);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (15, 'LG K500 X VIEW WHITE', 2, 5);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (16, 'SONY XPERIA XA ULTRA DUAL F3212 WHITE', 2, 3);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (17, 'SONY XPERIA XA ULTRA DL F3212 GRAPH BLACK', 2, 3);
--adding rest items
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (18, 'АК-Р SAMSUNG EB595675LU', 5, 1);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (19, 'LENOVO BL192', 5, 6);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (20, 'SAMSUNG ACADU10CBECSTD', 4, 1);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (21, 'SAMSUNG ETAOU10', 4, 1);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (22, 'SAMSUNG HS1303BEGRU', 3, 1);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (23, 'SAMSUNG HS1303WEGRU', 3, 1);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (24, 'Samsung In ear Fit', 3, 1);
--adding icons
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (25, 'fa fa-copyright', 8, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (26, 'fa fa-mobile', 8, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (27, 'fa fa-headphones', 8, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (28, 'fa fa-plug', 8, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (29, 'fa fa-bolt', 8, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (30, 'fa fa-user', 8, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (31, 'fa fa-shopping-cart', 8, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (32, 'fa fa-home', 8, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (33, 'fa fa-laptop', 8, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (34, 'fa fa-desktop', 8, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (35, 'fa fa-volume-up', 8, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (36, 'fa fa-clock-o', 8, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (37, 'fa fa-tablet', 8, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (38, 'fa fa-music', 8, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (39, 'fa fa-car', 8, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (40, 'fa fa-futbol-o', 8, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (41, 'fa fa-gamepad', 8, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (42, 'fa fa-gift', 8, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (43, 'fa fa-globe', 8, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (44, 'fa fa-money', 8, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (45, 'fa fa-video-camera', 8, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (46, 'fa fa-cc-visa', 8, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (47, 'fa fa-usd', 8, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (48, 'fa fa-eur', 8, null);
--adding parameters for phones
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (10, 'SAMSUNG SM-J700H GALAXY J7 DS BLACK', 2, 8);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (11, '5000', 3,  8);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (12, '/resources/img/SAMSUNGSM-J700HGALAXYJ7DSBLACK.jpg', 4,  8);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (13, '/resources/img/SAMSUNGSM-J700HGALAXYJ7DSBLACK-2.jpg', 5, 8);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (14, null, 6, 8);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (15, 'Android ', 7, 8);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (16, '5.5', 8, 8);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (17, '0', 9, 8);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (18, '10', 10, 8);
------------
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (19, 'SAMSUNG A510F GALAXY A5 BLACK', 2, 9);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (20, '8400', 3,  9);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (21, '/resources/img/SAMSUNGA510FGALAXYA5(2016)(BLACK).jpg', 4,  9);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (22, '/resources/img/SAMSUNGA510FGALAXYA5(2016)(BLACK)-2.jpg', 5, 9);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (23, '/resources/img/SAMSUNGA510FGALAXYA5(2016)(BLACK)-3.jpg', 6, 9);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (24, 'Android ', 7, 9);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (25, '5.2', 8, 9);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (26, '0', 9, 9);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (27, '10', 10, 9);
------------
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (28, 'SAMSUNG SM-J700H GALAXY J7 DS WHITE', 2, 10);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (29, '4900', 3,  10);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (30, '/resources/img/SAMSUNGSM-J700HGALAXYJ7DSWHITE.jpg', 4,  10);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (31, '/resources/img/SAMSUNGSM-J700HGALAXYJ7DSWHITE-2.jpg', 5, 10);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (32, '/resources/img/SAMSUNGSM-J700HGALAXYJ7DSWHITE-3.jpg', 6, 10);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (33, 'Android ', 7, 10);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (34, '5.5', 8, 10);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (35, '0', 9, 10);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (36, '10', 10, 10);
------------
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (37, 'LENOVO K5 (A6020A40) DUAL SIM GREY', 2, 11);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (38, '3800', 3, 11);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (39, '/resources/img/LENOVOK5(A6020A40)DUALSIM(GREY).jpg', 4,  11);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (40, '/resources/img/LENOVOK5(A6020A40)DUALSIM(GREY)-2.jpg', 5, 11);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (41, '/resources/img/LENOVOK5(A6020A40)DUALSIM(GREY)-3.jpg', 6, 11);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (42, 'Android ', 7, 11);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (43, '5', 8, 11);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (44, '0', 9, 11);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (45, '10', 10, 11);
------------
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (46, 'HTC DESIRE 630 DUAL SIM DARK GREY', 2, 12);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (47, '4999', 3,12);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (48, '/resources/img/HTCDESIRE630DUALSIMDARKGREY.jpg', 4,  12);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (49, '/resources/img/HTCDESIRE630DUALSIMDARKGREY-2.jpg', 5, 12);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (50, null, 6, 12);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (51, 'Android ', 7, 12);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (52, '5.5', 8,12);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (53, '0', 9, 12);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (54, '10', 10, 12);
------------
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (55, 'APPLE IPHONE 7 32GB ROSE GOLD', 2, 13);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (56, '19200', 3,  13);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (57, '/resources/img/IPHONE732GBROSEGOLD.jpg', 4,  13);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (58, '/resources/img/IPHONE732GBROSEGOLD-2.jpg', 5, 13);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (59, '/resources/img/IPHONE732GBROSEGOLD-3.jpg', 6, 13);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (60, 'iOS ', 7, 13);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (61, '4.7', 8, 13);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (62, '0', 9,13);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (63, '10', 10, 13);
------------
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (64, 'APPLE IPHONE 6S 128GB GOLD', 2, 14);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (65, '21300', 3,  14);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (66, '/resources/img/APPLEIPHONE6S128GBGOLD.jpg', 4,  14);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (67, '/resources/img/APPLEIPHONE6S128GBGOLD-2.jpg', 5, 14);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (68, '/resources/img/APPLEIPHONE6S128GBGOLD-3.jpg', 6, 14);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (69, 'iOS', 7, 14);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (70, '4.7', 8, 14);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (71, '0', 9, 14);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (72, '10', 10, 14);
------------
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (73, 'LG K500 X VIEW WHITE', 2, 15);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (74, '5700', 3,  15);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (75, '/resources/img/LGK500XVIEWWHITE.jpg', 4,  15);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (76, '/resources/img/LGK500XVIEWWHITE-2.jpg', 5, 15);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (77, '/resources/img/LGK500XVIEWWHITE-3.jpg', 6, 15);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (78, 'Android ', 7, 15);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (79, '5', 8, 15);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (80, '0', 9, 15);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (81, '10', 10, 15);
------------
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (82, 'SONY XPERIA XA ULTRA DUAL F3212 WHITE', 2, 16);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (83, '9200', 3,  16);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (84, '/resources/img/SONYXPERIAXAULTRADUALF3212(WHITE).jpg', 4,  16);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (85, '/resources/img/SONYXPERIAXAULTRADUALF3212(WHITE)-2.jpg', 5, 16);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (86, '/resources/img/SONYXPERIAXAULTRADUALF3212(WHITE)-3.jpg', 6, 16);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (87, 'Android ', 7, 16);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (88, '6', 8, 16);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (89, '0', 9, 16);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (90, '10', 10, 16);
------------
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (91, 'SONY XPERIA XA ULTRA DL F3212 GRAPH BLACK', 2, 17);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (92, '9200', 3,  17);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (93, '/resources/img/SONYXPERIAXAULTRADLF3212GRAPHBLACK.jpg', 4,  17);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (94, '/resources/img/SONYXPERIAXAULTRADLF3212GRAPHBLACK-2.jpg', 5, 17);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (95, '/resources/img/SONYXPERIAXAULTRADLF3212GRAPHBLACK-3.jpg', 6, 17);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (96, 'Android ', 7, 17);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (97, '6', 8, 17);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (98, '0', 9, 17);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (99, '10', 10, 17);
---adding parameters for batteries
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (100, 'Battery AK-P SAMSUNG EB595675LU', 27, 18);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (101, '300', 28, 18);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (102, '0', 29, 18);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (103, '10', 30, 18);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (104, '3100 mAh', 31, 18);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (105, '/resources/img/AK-PSAMSUNGEB595675LU.jpg', 32, 18);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (106, null, 33, 18);
------------
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (107, 'Phone battery LENOVO BL192', 27, 19);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (108, '300', 28, 19);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (109, '0', 29, 19);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (110, '10', 30, 19);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (111, '2000 mAh', 31, 19);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (112, '/resources/img/LENOVOBL192(2000MAH).jpg', 32, 19);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (113, null, 33, 19);
---adding parameters for chargers
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (114, 'SAMSUNG ACADU10 CAR CHARGER', 19, 20);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (115, '250', 20, 20);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (116, '1.5 m', 21, 20);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (117, '0', 22, 20);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (118, '10', 23, 20);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (119, '0.7 A', 24, 20);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (120, '/resources/img/SAMSUNGACADU10CBECSTD(BLACK)UNIVERSALCARCHARGER.jpg', 25, 20);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (121, '/resources/img/SAMSUNGACADU10CBECSTD(BLACK)UNIVERSALCARCHARGER-2.jpg', 26, 20);
------------
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (122, 'Charger SAMSUNG ETAOU10', 19, 21);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (123, '150', 20, 21);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (124, '1.2 m', 21, 21);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (125, '0', 22, 21);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (126, '10', 23, 21);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (127, '0.7 A', 24, 21);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (128, '/resources/img/SAMSUNGETAOU10.jpg', 25, 21);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (129, '/resources/img/SAMSUNGETAOU10-2.jpg', 26, 21);
---adding parameters for headphones
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (130, 'Headset SAMSUNG HS1303BEGRU Black', 11, 22);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (131, '300', 12, 22);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (132, '/resources/img/SAMSUNGHS1303BEGRUBlack.jpg', 13, 22);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (133, null, 14, 22);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (134, null, 15, 22);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (135, '-', 16, 22);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (136, '0', 17, 22);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (137, '10', 18, 22);
------------
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (138, 'Headset SAMSUNG HS1303BEGRU Black', 11, 23);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (139, '300', 12, 23);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (140, '/resources/img/SAMSUNGHS1303WEGRUWhite.jpg', 13, 23);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (141, null, 14, 23);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (142, null, 15, 23);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (143, '-', 16, 23);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (144, '0', 17, 23);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (145, '10', 18, 23);
------------
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (146, 'Headset Samsung In ear Fit EO-EG920LWEGRU White', 11, 24);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (147, '450', 12, 24);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (148, '/resources/img/SamsungInearFitEO-EG920LWEGRUWhite.jpg', 13, 24);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (149, '/resources/img/SamsungInearFitEO-EG920LWEGRUWhite-2.jpg', 14, 24);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (150, null, 15, 24);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (151, '+', 16, 24);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (152, '0', 17, 24);
insert into lab3_PARAMETERS (parameter_id, value, attribute_id, object_id) values (153, '10', 18, 24);
------------
ALTER SEQUENCE HIBERNATE_SEQUENCE increment by 2000;
select HIBERNATE_SEQUENCE.NEXTVAL from dual;
ALTER SEQUENCE HIBERNATE_SEQUENCE increment by 1;
------------
commit;
/


