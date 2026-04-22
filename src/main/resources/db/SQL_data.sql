INSERT
IGNORE INTO tag (tag_name) VALUES
('Elektronik'),
('Tøj'),
('Sko'),
('Parfume'),
('Smykker'),
('Accessories'),
('Boligindretning'),
('Møbler'),
('Køkkenudstyr'),
('Bøger'),
('Spil'),
('Gaming'),
('Sport'),
('Fitness'),
('Rejser'),
('Oplevelser'),
('Skønhed'),
('Plejeprodukter'),
('Gadgets'),
('Legetøj');

insert into USERS (username, password)
VALUES ('Admin', '1234'),
       ('Signe', '1234'),
       ('Mads', '1234'),
       ('Ian', '1234');

INSERT INTO wish_list (user_id, name)
VALUES
-- Admin
(1, 'Admin Jul 2026'),
(1, 'Admin Fødselsdag'),
(1, 'Admin Tech Wishlist'),

-- Signe
(2, 'Signe Jul 2026'),
(2, 'Signe Fødselsdag'),
(2, 'Signe Selvforkælelse'),

-- Mads
(3, 'Mads Jul 2026'),
(3, 'Mads Fødselsdag'),
(3, 'Mads Gaming Gear'),

-- Ian
(4, 'Ian Jul 2026'),
(4, 'Ian Fødselsdag'),
(4, 'Ian Fitness & Sport');

INSERT INTO wish (name, description, price, quantity, product_link, wish_list_id)
VALUES

-- Admin Jul 2026 (wish_list_id = 1)
('LEGO Star Wars Millennium Falcon', 'Stor LEGO model til samlingen', 1299.95, 1,
 'https://www.lego.com/da-dk/search?q=millennium%20falcon', 1),
('Mekanisk tastatur', 'Gaming/arbejdstastatur med RGB', 799.00, 1, 'https://www.proshop.dk/?s=mekanisk+tastatur', 1),
('Trådløse høretelefoner', 'Over-ear headphones med støjreduktion', 1499.00, 1,
 'https://www.elgiganten.dk/search/tr%C3%A5dl%C3%B8se%20h%C3%B8retelefoner', 1),
('Kaffemaskine', 'Automatisk kaffemaskine til hjemmet', 2499.00, 1, 'https://www.power.dk/search/?q=kaffemaskine', 1),
('Smartwatch', 'Smart ur til hverdag og træning', 1999.00, 1, 'https://www.elgiganten.dk/search/smartwatch', 1),

-- Admin Fødselsdag 2026 (wish_list_id = 2)
('Airfryer', 'Airfryer til hurtig madlavning', 899.00, 1, 'https://www.power.dk/search/?q=airfryer', 2),
('LEGO Technic Ferrari', 'LEGO Technic bilmodel', 1499.95, 1, 'https://www.lego.com/da-dk/search?q=ferrari', 2),
('Bose Bluetooth højtaler', 'Bærbar højtaler med god lyd', 1199.00, 1,
 'https://www.elgiganten.dk/search/bluetooth%20h%C3%B8jtaler', 2),
('Skjorte', 'Pæn skjorte til arbejde og fest', 399.00, 2, 'https://www.zalando.dk/herretoj-skjorter/', 2),
('Steam gavekort', 'Gavekort til spil på Steam', 300.00, 1, 'https://store.steampowered.com/digitalgiftcards/', 2),

-- Admin Tech Wishlist (wish_list_id = 3)
('27 tommer skærm', 'Ekstra monitor til setup', 1799.00, 1, 'https://www.proshop.dk/?s=27+tommer+sk%C3%A6rm', 3),
('USB-C docking station', 'Dock til laptop og skærme', 1099.00, 1, 'https://www.proshop.dk/?s=usb-c+docking+station',
 3),
('Logitech MX Master mus', 'Ergonomisk trådløs mus', 799.00, 1,
 'https://www.logitech.com/da-dk/search.html?q=mx%20master', 3),
('Ekstern SSD 1TB', 'Hurtig SSD til backup', 899.00, 1, 'https://www.proshop.dk/?s=ekstern+ssd+1tb', 3),
('Webcam', 'HD webcam til møder og streaming', 599.00, 1, 'https://www.elgiganten.dk/search/webcam', 3),

-- Signe Jul 2026 (wish_list_id = 4)
('Parfume gaveæske', 'Lækker gaveæske med duft og lotion', 699.00, 1, 'https://www.matas.dk/search?q=parfume', 4),
('Smykkeskrin', 'Praktisk skrin til smykker', 349.00, 1, 'https://www.ikea.com/dk/da/search/?q=opbevaring', 4),
('Blødt tæppe', 'Varmt tæppe til sofaen', 299.00, 1, 'https://www.ikea.com/dk/da/search/?q=t%C3%A6ppe', 4),
('LEGO blomsterbuket', 'Dekorativ LEGO blomsterbuket', 499.95, 1, 'https://www.lego.com/da-dk/search?q=flowers', 4),
('Ansigtspleje sæt', 'Hudplejesæt til vinteren', 499.00, 1, 'https://www.matas.dk/search?q=hudpleje', 4),

-- Signe Fødselsdag 2026 (wish_list_id = 5)
('Armbånd', 'Elegant armbånd i sølvlook', 450.00, 1, 'https://www.pilgrim.net/da-dk/search?type=product&q=armb%C3%A5nd',
 5),
('Håndtaske', 'Lille taske til hverdag og bytur', 699.00, 1, 'https://www.zalando.dk/dametasker/', 5),
('Silketørklæde', 'Pænt tørklæde i lyse farver', 299.00, 1, 'https://www.zalando.dk/accessories/', 5),
('Bestseller roman', 'Ny bog til afslapning', 249.00, 1, 'https://www.saxo.com/dk/products/search?query=roman', 5),
('Duftlys', 'Velduftende lys til hjemmet', 199.00, 2, 'https://www.ikea.com/dk/da/search/?q=duftlys', 5),

-- Signe Selvforkælelse (wish_list_id = 6)
('Spa gavekort', 'Wellness og afslapning', 1000.00, 1, 'https://www.smartbox.com/dk/', 6),
('Ansigtsmaske sæt', 'Skønhedsprodukter til hjemme-spa', 199.00, 1, 'https://www.matas.dk/search?q=ansigtsmaske', 6),
('Morgenkåbe', 'Blød morgenkåbe til afslapning', 399.00, 1, 'https://www.zalando.dk/dametoej-morgenkaaber/', 6),
('Massagepude', 'Elektrisk massagepude til nakke og ryg', 499.00, 1, 'https://www.power.dk/search/?q=massagepude', 6),
('Hudpleje kalender', 'Skønhedsprodukter samlet i gaveæske', 599.00, 1, 'https://www.matas.dk/search?q=gave%C3%A6ske',
 6),

-- Mads Jul 2026 (wish_list_id = 7)
('PlayStation 5 spil', 'Nyt actionspil til PS5', 549.00, 1,
 'https://www.elgiganten.dk/gaming/playstation/playstation-spil', 7),
('LEGO Speed Champions', 'Sjov LEGO bil til samlingen', 249.95, 1, 'https://www.lego.com/da-dk/themes/speed-champions',
 7),
('Gaming headset', 'Headset med god mikrofon', 699.00, 1, 'https://www.proshop.dk/?s=gaming+headset', 7),
('Hoodie', 'Komfortabel hoodie i mørke farver', 399.00, 2,
 'https://www.zalando.dk/herretoej-sweatshirts-haettetroejer/', 7),
('FIFA / EA Sports FC', 'Fodboldspil til konsol', 499.00, 1, 'https://www.elgiganten.dk/search/ea%20sports%20fc', 7),

-- Mads Fødselsdag 2026 (wish_list_id = 8)
('Kontorstol', 'Bedre stol til gaming og studie', 1499.00, 1, 'https://www.ikea.com/dk/da/search/?q=kontorstol', 8),
('Bluetooth højtaler', 'Bærbar speaker til værelset', 799.00, 1,
 'https://www.power.dk/search/?q=bluetooth+h%C3%B8jtaler', 8),
('Skærmarm', 'Arm til monitor på skrivebordet', 599.00, 1, 'https://www.proshop.dk/?s=sk%C3%A6rmarm', 8),
('Sneakers', 'Nye sneakers til hverdag', 899.00, 1, 'https://www.zalando.dk/herresko-sneakers/', 8),
('Steam gavekort', 'Til nye spil i biblioteket', 500.00, 1, 'https://store.steampowered.com/digitalgiftcards/', 8),

-- Mads Gaming Gear (wish_list_id = 9)
('Gaming mus', 'Præcis mus til FPS spil', 499.00, 1, 'https://www.proshop.dk/?s=gaming+mus', 9),
('Musemåtte XL', 'Stor musemåtte til skrivebordet', 249.00, 1, 'https://www.proshop.dk/?s=xl+musem%C3%A5tte', 9),
('RGB tastatur', 'Mekanisk tastatur med lys', 899.00, 1, 'https://www.elgiganten.dk/search/rgb%20tastatur', 9),
('Gaming skærm 144Hz', 'Skærm med høj opdateringshastighed', 1899.00, 1,
 'https://www.proshop.dk/?s=144hz+gaming+sk%C3%A6rm', 9),
('Mikrofon til Discord', 'USB mikrofon til gaming og chat', 649.00, 1,
 'https://www.elgiganten.dk/search/usb%20mikrofon', 9),

-- Ian Jul 2026 (wish_list_id = 10)
('Løbesko', 'Gode sko til træning og løb', 999.00, 1, 'https://www.zalando.dk/sport-loebesko/', 10),
('Træningstaske', 'Taske til fitnessudstyr', 349.00, 1, 'https://www.zalando.dk/sport-tasker/', 10),
('Vandflaske', 'Stor drikkedunk til træning', 149.00, 1, 'https://www.proshop.dk/?s=drikkedunk', 10),
('LEGO Technic motorcykel', 'Sjov model at bygge', 799.95, 1, 'https://www.lego.com/da-dk/search?q=motorcycle', 10),
('Træningsjakke', 'Let jakke til udendørs brug', 499.00, 1, 'https://www.zalando.dk/sport-jakker/', 10),

-- Ian Fødselsdag 2026 (wish_list_id = 11)
('Justerbare håndvægte', 'Håndvægte til hjemmetræning', 1799.00, 1, 'https://www.proshop.dk/?s=h%C3%A5ndv%C3%A6gte',
 11),
('Protein shaker', 'Shaker til proteinpulver', 99.00, 2, 'https://www.proshop.dk/?s=shaker', 11),
('Træningsmåtte', 'Måtte til udstrækning og core', 199.00, 1, 'https://www.power.dk/search/?q=tr%C3%A6ningsm%C3%A5tte',
 11),
('Sportsur', 'Ur med træningsfunktioner', 1499.00, 1, 'https://www.elgiganten.dk/search/sportsur', 11),
('Cykelhjelm', 'Sikker hjelm til pendling og motion', 599.00, 1, 'https://www.zalando.dk/sport-cykelhjelm/', 11),

-- Ian Fitness & Sport (wish_list_id = 12)
('Pull-up bar', 'Dørstang til hjemmetræning', 299.00, 1, 'https://www.proshop.dk/?s=pull+up+bar', 12),
('Kettlebell', 'Kettlebell til styrketræning', 349.00, 1, 'https://www.proshop.dk/?s=kettlebell', 12),
('Træningselastikker', 'Elastikker til mobilitet og styrke', 149.00, 1,
 'https://www.power.dk/search/?q=tr%C3%A6ningselastikker', 12),
('Løbejakke', 'Jakke til løb i koldt vejr', 599.00, 1, 'https://www.zalando.dk/sport-loebejakker/', 12),
('Foam roller', 'Til restitution og opvarmning', 199.00, 1, 'https://www.proshop.dk/?s=foam+roller', 12);

INSERT
IGNORE INTO wish_tag (wish_id, tag_id)
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Legetøj'
WHERE w.name = 'LEGO Star Wars Millennium Falcon'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Gaming'
WHERE w.name = 'Mekanisk tastatur'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Elektronik'
WHERE w.name = 'Mekanisk tastatur'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Elektronik'
WHERE w.name = 'Trådløse høretelefoner'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Køkkenudstyr'
WHERE w.name = 'Kaffemaskine'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Elektronik'
WHERE w.name = 'Smartwatch'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Fitness'
WHERE w.name = 'Smartwatch'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Køkkenudstyr'
WHERE w.name = 'Airfryer'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Legetøj'
WHERE w.name = 'LEGO Technic Ferrari'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Elektronik'
WHERE w.name = 'Bose Bluetooth højtaler'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Tøj'
WHERE w.name = 'Skjorte'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Spil'
WHERE w.name = 'Steam gavekort'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Elektronik'
WHERE w.name = '27 tommer skærm'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Elektronik'
WHERE w.name = 'USB-C docking station'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Elektronik'
WHERE w.name = 'Logitech MX Master mus'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Gadgets'
WHERE w.name = 'Ekstern SSD 1TB'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Elektronik'
WHERE w.name = 'Webcam'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Parfume'
WHERE w.name = 'Parfume gaveæske'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Smykker'
WHERE w.name = 'Smykkeskrin'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Boligindretning'
WHERE w.name = 'Blødt tæppe'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Legetøj'
WHERE w.name = 'LEGO blomsterbuket'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Skønhed'
WHERE w.name = 'Ansigtspleje sæt'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Plejeprodukter'
WHERE w.name = 'Ansigtspleje sæt'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Smykker'
WHERE w.name = 'Armbånd'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Accessories'
WHERE w.name = 'Håndtaske'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Accessories'
WHERE w.name = 'Silketørklæde'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Bøger'
WHERE w.name = 'Bestseller roman'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Boligindretning'
WHERE w.name = 'Duftlys'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Oplevelser'
WHERE w.name = 'Spa gavekort'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Skønhed'
WHERE w.name = 'Ansigtsmaske sæt'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Plejeprodukter'
WHERE w.name = 'Ansigtsmaske sæt'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Tøj'
WHERE w.name = 'Morgenkåbe'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Plejeprodukter'
WHERE w.name = 'Massagepude'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Skønhed'
WHERE w.name = 'Hudpleje kalender'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Spil'
WHERE w.name = 'PlayStation 5 spil'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Gaming'
WHERE w.name = 'PlayStation 5 spil'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Legetøj'
WHERE w.name = 'LEGO Speed Champions'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Gaming'
WHERE w.name = 'Gaming headset'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Elektronik'
WHERE w.name = 'Gaming headset'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Tøj'
WHERE w.name = 'Hoodie'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Spil'
WHERE w.name = 'FIFA / EA Sports FC'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Gaming'
WHERE w.name = 'FIFA / EA Sports FC'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Møbler'
WHERE w.name = 'Kontorstol'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Elektronik'
WHERE w.name = 'Bluetooth højtaler'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Elektronik'
WHERE w.name = 'Skærmarm'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Sko'
WHERE w.name = 'Sneakers'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Spil'
WHERE w.name = 'Steam gavekort'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Gaming'
WHERE w.name = 'Gaming mus'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Gaming'
WHERE w.name = 'Musemåtte XL'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Gaming'
WHERE w.name = 'RGB tastatur'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Elektronik'
WHERE w.name = 'RGB tastatur'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Gaming'
WHERE w.name = 'Gaming skærm 144Hz'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Elektronik'
WHERE w.name = 'Gaming skærm 144Hz'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Gaming'
WHERE w.name = 'Mikrofon til Discord'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Elektronik'
WHERE w.name = 'Mikrofon til Discord'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Sko'
WHERE w.name = 'Løbesko'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Sport'
WHERE w.name = 'Løbesko'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Sport'
WHERE w.name = 'Træningstaske'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Fitness'
WHERE w.name = 'Vandflaske'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Legetøj'
WHERE w.name = 'LEGO Technic motorcykel'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Sport'
WHERE w.name = 'Træningsjakke'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Tøj'
WHERE w.name = 'Træningsjakke'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Fitness'
WHERE w.name = 'Justerbare håndvægte'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Fitness'
WHERE w.name = 'Protein shaker'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Fitness'
WHERE w.name = 'Træningsmåtte'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Elektronik'
WHERE w.name = 'Sportsur'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Fitness'
WHERE w.name = 'Sportsur'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Sport'
WHERE w.name = 'Cykelhjelm'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Fitness'
WHERE w.name = 'Pull-up bar'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Fitness'
WHERE w.name = 'Kettlebell'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Fitness'
WHERE w.name = 'Træningselastikker'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Sport'
WHERE w.name = 'Løbejakke'

UNION ALL
SELECT w.wish_id, t.tag_id
FROM wish w
         JOIN tag t ON t.tag_name = 'Fitness'
WHERE w.name = 'Foam roller';