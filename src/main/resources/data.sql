-- Address
INSERT INTO address (street, postalCode, city) VALUES
('Köpmangatan 14', '861 31', 'Timrå'),
('Bultgatan 4', '853 50', 'Sundsvall'),
('Skatan 192', '862 96', 'Njurunda'),
('11 Solhemsvägen', '861 42', 'Sörberge'),
('Södra Allén 20', '852 39', 'Sundsvall');
-- Member
INSERT INTO member (first_name, last_name, email, phone, date_of_birth, address_id) VALUES
('Scott', 'Payne', 'payne@gmail.com', '0701715411', '1995-04-12', 1),
('Scott', 'Payne', 'payne@gmail.com', '0727485236', '1965-01-07', 1),
('Scott', 'Payne', 'payne@gmail.com', '0733694129', '1995-02-06', 1),
('Scott', 'Payne', 'payne@gmail.com', '0708451240', '1995-03-11', 1),
('Scott', 'Payne', 'payne@gmail.com', '0736819040', '1995-05-10', 1);
