insert into LAB3_OBJECT_TYPES (OBJECT_TYPE_ID, NAME, parent_id) values (1, 'brand', null);
insert into LAB3_OBJECT_TYPES (OBJECT_TYPE_ID, NAME, parent_id) values (2, 'Phone', null);
insert into LAB3_OBJECT_TYPES (OBJECT_TYPE_ID, NAME, parent_id) values (3, 'Headphones', 2);
insert into LAB3_OBJECT_TYPES (OBJECT_TYPE_ID, NAME, parent_id) values (4, 'Charger',  2);
insert into LAB3_OBJECT_TYPES (OBJECT_TYPE_ID, NAME, parent_id) values (5, 'Battery', 2);
insert into LAB3_OBJECT_TYPES (OBJECT_TYPE_ID, NAME, parent_id) values (6, 'user', null);
insert into LAB3_OBJECT_TYPES (OBJECT_TYPE_ID, NAME, parent_id) values (7, 'cart', 6);
--attributes for brand
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (1, 'logo', 1);
--attributes for phone
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (2, 'name', 2);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (3, 'price', 2);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (4, 'icon', 2);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (5, 'icon2', 2);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (6, 'icon3', 2);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (7, 'OS', 2);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (8, 'diagonal', 2);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (9, 'rating', 2);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (10, 'quantity', 2);
--attributes for headphones
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (11, 'name', 3);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (12, 'price', 3);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (13, 'icon', 3);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (14, 'icon2', 3);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (15, 'icon3', 3);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (16, 'microphone', 3);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (17, 'rating', 3);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (18, 'quantity', 3);
--attributes for charger
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (19,  'name', 4);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (20, 'price', 4);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (21, 'cable length', 4);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (22, 'rating', 4);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (23, 'quantity', 4);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (24, 'power', 4);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (25, 'icon', 4);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (26, 'icon2', 4);
--attributes for battery
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (27, 'name', 5);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (28, 'price', 5);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (29, 'rating', 5);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (30, 'quantity', 5);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (31, 'capacity', 5);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (32, 'icon', 5);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (33, 'icon2', 5);
--attributes for user
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (34, 'login', 6);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (35, 'password', 6);
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (36, 'role', 6);
--attribute for cart
insert INTO lab3_attributes (ATTRIBUTE_ID, NAME, OBJECT_TYPE_ID) values (37, 'item', 7);
--adding brands
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (1, 'Samsung', 1, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (2, 'HTC', 1, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (3, 'Sony', 1, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (4, 'Apple', 1, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (5, 'LG', 1, null);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (6, 'Lenovo', 1, null);
--adding brands logos
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (1, '/resources/img/brand3.png', 1, 1);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (2, '/resources/img/brand5.png', 1, 2);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (3, '/resources/img/brand1.png', 1, 3);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (4, '/resources/img/brand4.png', 1, 4);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (5, '/resources/img/brand6.png', 1, 5);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (6, '/resources/img/brand2.png', 1, 6);
--adding phones
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (7, 'SM-J700H GALAXY J7 DS BLACK', 2, 1);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (8, 'A510F GALAXY A5', 2, 1);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (9, 'SM-J700H GALAXY J7 DS WHITE', 2, 1);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (10, 'K5 (A6020A40) DUAL SIM GREY', 2, 6);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (11, 'DESIRE 630 DUAL SIM DARK GREY', 2, 2);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (12, 'IPHONE 7 32GB ROSE', 2, 4);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (13, 'IPHONE 6S 128GB GOLD', 2, 4);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (14, 'K500 X VIEW WHITE', 2, 5);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (15, 'XPERIA XA ULTRA DUAL F3212 WHITE', 2, 3);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (16, 'XPERIA XA ULTRA DL F3212 GRAPH BLACK', 2, 3);
--adding rest items
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (17, 'АК-Р SAMSUNG EB595675LU', 5, 1);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (18, 'LENOVO BL192', 5, 6);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (19, 'SAMSUNG ACADU10CBECSTD', 4, 1);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (20, 'SAMSUNG ETAOU10', 4, 1);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (21, 'SAMSUNG HS1303BEGRU', 3, 1);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (22, 'SAMSUNG HS1303WEGRU', 3, 1);
insert into LAB3_OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID, PARENT_ID) values (23, 'Samsung In ear Fit', 3, 1);
--adding parameters for phones
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (7, 'SAMSUNG SM-J700H GALAXY J7 DS BLACK', 2, 7);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (8, '5000', 3,  7);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (9, '/resources/img/SAMSUNGSM-J700HGALAXYJ7DSBLACK.jpg', 4,  7);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (10, '/resources/img/SAMSUNGSM-J700HGALAXYJ7DSBLACK-2.jpg', 5, 7);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (11, null, 6, 7);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (12, 'Android ', 7, 7);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (13, '5.5', 8, 7);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (14, '0', 9, 7);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (15, '10', 10, 7);
------------
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (16, 'SAMSUNG A510F GALAXY A5 BLACK', 2, 8);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (17, '8400', 3,  8);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (18, '/resources/img/SAMSUNGA510FGALAXYA5(2016)(BLACK).jpg', 4,  8);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (19, '/resources/img/SAMSUNGA510FGALAXYA5(2016)(BLACK)-2.jpg', 5, 8);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (20, '/resources/img/SAMSUNGA510FGALAXYA5(2016)(BLACK)-3.jpg', 6, 8);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (21, 'Android ', 7, 8);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (22, '5.2', 8, 8);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (23, '0', 9, 8);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (24, '10', 10, 8);
------------
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (25, 'SAMSUNG SM-J700H GALAXY J7 DS WHITE', 2, 9);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (26, '4900', 3,  9);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (27, '/resources/img/SAMSUNGSM-J700HGALAXYJ7DSWHITE.jpg', 4,  9);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (28, '/resources/img/SAMSUNGSM-J700HGALAXYJ7DSWHITE-2.jpg', 5, 9);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (29, '/resources/img/SAMSUNGSM-J700HGALAXYJ7DSWHITE-3.jpg', 6, 9);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (30, 'Android ', 7, 9);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (31, '5.5', 8, 9);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (32, '0', 9, 9);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (33, '10', 10, 9);
------------
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (34, 'LENOVO K5 (A6020A40) DUAL SIM GREY', 2, 10);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (35, '3800', 3, 10);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (36, '/resources/img/LENOVOK5(A6020A40)DUALSIM(GREY).jpg', 4,  10);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (37, '/resources/img/LENOVOK5(A6020A40)DUALSIM(GREY)-2.jpg', 5, 10);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (38, '/resources/img/LENOVOK5(A6020A40)DUALSIM(GREY)-3.jpg', 6, 10);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (39, 'Android ', 7, 10);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (40, '5', 8, 10);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (41, '0', 9, 10);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (42, '10', 10, 10);
------------
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (43, 'HTC DESIRE 630 DUAL SIM DARK GREY', 2, 11);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (44, '4999', 3,11);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (45, '/resources/img/HTCDESIRE630DUALSIMDARKGREY.jpg', 4,  11);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (46, '/resources/img/HTCDESIRE630DUALSIMDARKGREY-2.jpg', 5, 11);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (47, null, 6, 11);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (48, 'Android ', 7, 11);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (49, '5.5', 8,11);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (50, '0', 9, 11);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (51, '10', 10, 11);
------------
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (52, 'IPHONE 7 32GB ROSE GOLD', 2, 12);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (53, '19200', 3,  12);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (54, '/resources/img/IPHONE732GBROSEGOLD.jpg', 4,  12);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (55, '/resources/img/IPHONE732GBROSEGOLD-2.jpg', 5, 12);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (56, '/resources/img/IPHONE732GBROSEGOLD-3.jpg', 6, 12);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (57, 'iOS ', 7, 12);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (58, '4.7', 8, 12);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (59, '0', 9,12);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (60, '10', 10, 12);
------------
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (61, 'IPHONE 6S 128GB GOLD', 2, 13);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (62, '21300', 3,  13);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (63, '/resources/img/APPLEIPHONE6S128GBGOLD.jpg', 4,  13);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (64, '/resources/img/APPLEIPHONE6S128GBGOLD-2.jpg', 5, 13);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (65, '/resources/img/APPLEIPHONE6S128GBGOLD-3.jpg', 6, 13);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (66, 'iOS', 7, 13);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (67, '4.7', 8, 13);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (68, '0', 9, 13);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (69, '10', 10, 13);
------------
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (70, 'LG K500 X VIEW WHITE', 2, 14);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (71, '5700', 3,  14);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (72, '/resources/img/LGK500XVIEWWHITE.jpg', 4,  14);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (73, '/resources/img/LGK500XVIEWWHITE-2.jpg', 5, 14);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (74, '/resources/img/LGK500XVIEWWHITE-3.jpg', 6, 14);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (75, 'Android ', 7, 14);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (76, '5', 8, 14);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (77, '0', 9, 14);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (78, '10', 10, 14);
------------
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (79, 'SONY XPERIA XA ULTRA DUAL F3212 WHITE', 2, 15);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (80, '9200', 3,  15);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (81, '/resources/img/SONYXPERIAXAULTRADUALF3212(WHITE).jpg', 4,  15);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (82, '/resources/img/SONYXPERIAXAULTRADUALF3212(WHITE)-2.jpg', 5, 15);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (83, '/resources/img/SONYXPERIAXAULTRADUALF3212(WHITE)-3.jpg', 6, 15);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (84, 'Android ', 7, 15);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (85, '6', 8, 15);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (86, '0', 9, 15);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (87, '10', 10, 15);
------------
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (88, 'SONY XPERIA XA ULTRA DL F3212 GRAPH BLACK', 2, 16);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (89, '9200', 3,  16);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (90, '/resources/img/SONYXPERIAXAULTRADLF3212GRAPHBLACK.jpg', 4,  16);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (91, '/resources/img/SONYXPERIAXAULTRADLF3212GRAPHBLACK-2.jpg', 5, 16);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (92, '/resources/img/SONYXPERIAXAULTRADLF3212GRAPHBLACK-3.jpg', 6, 16);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (93, 'Android ', 7, 16);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (94, '6', 8, 16);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (95, '0', 9, 16);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (96, '10', 10, 16);
---adding parameters for batteries
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (97, 'Phone battery AK-P SAMSUNG EB595675LU', 27, 17);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (98, '300', 28, 17);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (99, '0', 29, 17);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (100, '10', 30, 17);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (101, '3100 mAh', 31, 17);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (102, '/resources/img/AK-PSAMSUNGEB595675LU.jpg', 32, 17);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (103, null, 33, 17);
------------
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (104, 'Phone battery LENOVO BL192', 27, 18);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (105, '300', 28, 18);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (106, '0', 29, 18);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (107, '10', 30, 18);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (108, '2000 mAh', 31, 18);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (109, '/resources/img/LENOVOBL192(2000MAH).jpg', 32, 18);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (110, null, 33, 18);
---adding parameters for chargers
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (111, 'Charger SAMSUNG ACADU10CBECSTD (BLACK) UNIVERSAL CAR CHARGER', 19, 19);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (112, '250', 20, 19);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (113, '1.5 m', 21, 19);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (114, '0', 22, 19);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (115, '10', 23, 19);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (116, '0.7 A', 24, 19);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (117, '/resources/img/SAMSUNGACADU10CBECSTD(BLACK)UNIVERSALCARCHARGER.jpg', 25, 19);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (118, '/resources/img/SAMSUNGACADU10CBECSTD(BLACK)UNIVERSALCARCHARGER-2.jpg', 26, 19);
------------
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (119, 'Charger SAMSUNG ETAOU10', 19, 20);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (120, '150', 20, 20);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (121, '1.2 m', 21, 20);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (122, '0', 22, 20);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (123, '10', 23, 20);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (124, '0.7 A', 24, 20);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (125, '/resources/img/SAMSUNGETAOU10.jpg', 25, 20);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (126, '/resources/img/SAMSUNGETAOU10-2.jpg', 26, 20);
---adding parameters for headphones
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (127, 'Headset SAMSUNG HS1303BEGRU Black', 11, 21);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (128, '300', 12, 21);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (129, '/resources/img/SAMSUNGHS1303BEGRUBlack.jpg', 13, 21);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (130, null, 14, 21);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (131, null, 15, 21);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (132, '-', 16, 21);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (133, '0', 17, 21);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (134, '10', 18, 21);
------------
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (135, 'Headset SAMSUNG HS1303BEGRU Black', 11, 22);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (136, '300', 12, 22);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (137, '/resources/img/SAMSUNGHS1303WEGRUWhite.jpg', 13, 22);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (138, null, 14, 22);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (139, null, 15, 22);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (140, '-', 16, 22);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (141, '0', 17, 22);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (142, '10', 18, 22);
------------
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (143, 'Headset Samsung In ear Fit EO-EG920LWEGRU White', 11, 23);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (144, '450', 12, 23);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (145, '/resources/img/SamsungInearFitEO-EG920LWEGRUWhite.jpg', 13, 23);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (146, '/resources/img/SamsungInearFitEO-EG920LWEGRUWhite-2.jpg', 14, 23);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (147, null, 15, 23);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (148, '+', 16, 23);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (149, '0', 17, 23);
insert into lab3_params (parameter_id, value, attribute_id, object_id) values (150, '10', 18, 23);
------------
ALTER SEQUENCE HIBERNATE_SEQUENCE increment by 200;
select HIBERNATE_SEQUENCE.NEXTVAL from dual;
ALTER SEQUENCE HIBERNATE_SEQUENCE increment by 1;
------------
commit;
/


