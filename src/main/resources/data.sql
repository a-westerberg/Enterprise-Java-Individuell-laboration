INSERT INTO address (street, postal_code, city) VALUES
('Köpmangatan 14', '861 31', 'Timrå'),
('Bultgatan 4', '853 50', 'Sundsvall'),
('Skatan 192', '862 96', 'Njurunda'),
('11 Solhemsvägen', '861 42', 'Sörberge'),
('Södra Allén 20', '852 39', 'Sundsvall');

INSERT INTO member (first_name, last_name, email, phone, date_of_birth, address_id) VALUES
('Scott', 'Payne', 'payne@gmail.com', '0701715411', '1995-04-12', 1),
('Angelica', 'Andersson', 'angelica@gmail.com', '0727485236', '1965-01-07', 1),
('Freja', 'White', 'freja@gmail.com', '0733694129', '1972-02-06', 3),
('Erling', 'Haaland', 'erling@gmail.com', '0708451240', '2000-03-11', 4),
('Kjertil', 'Västsjö', 'kjertil@gmail.com', '0736819040', '1987-05-10', 5);
