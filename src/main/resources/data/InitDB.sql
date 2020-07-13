INSERT INTO Category VALUES
(1, 'Novel'),
(2, 'Fiction'),
(3, 'Poetry');

INSERT INTO Publisher VALUES
(1, 'The Old Lion Publishers', 'https://starylev.com.ua/'),
(2, 'Astrolabe Publishers', 'https://astrolabium.com.ua/'),
(3, 'Folio Publishers', 'https://foliobooks.com.ua/');

INSERT INTO Books VALUES
(1, 'The Hunger Games', 'Suzanne Collins', 1, 2, 25.99),
(2, 'The Book Thief', 'Markus Zusak', 1, 1, 40),
(3, 'The Giving Tree', 'Shel Silverstein', 2, 1, 15.50),
(4, 'The Picture of Dorian Gray', 'Oscar Wilde', 1, 3, 32),
(5, 'Lord of the Flies', 'William Golding', 3, 2, 40.99);

INSERT INTO Member VALUES
(1, 'Bohdan', 'Ivanchyshyn', 'ui22io', '+380967971724', 'bohdan20032001@gmail.com', 'Lviv'),
(2, 'Roman', 'Koblyk', 'or9u6q', '+380974543567', 'r_koblyk21@gmail.com', 'Lviv'),
(3, 'Andrii', 'Zaremba', 'q23er4', '+380977193821', 'zaremba0405@gmail.com', 'Lviv'),
(4, 'Vitalii', 'Hrymovskyi', 'iu4t67', '+380962334569', 'h_vitalii2345@gmail.com', 'Lviv'),
(5, 'Mykhailo', 'Zakharchuk', 'er56yu', '+380967476825', 'mzakharchuk@gmail.com', 'Lviv');

INSERT INTO Librarian VALUES
(1, 'Olha', 'Kramarchuk', '65tyh7', '+380975453234', 'o_kramarchuk@gmail.com', 3),
(2, 'Viktor', 'Shevchuk', '7t5dsv3', '+380977342311', 'viktor1985@gmail.com', 1),
(3, 'Kateryna', 'Hnatiuk', 'zza43q', '+380965534213', 'kateryna_hnatiuk32@gmail.com', 2);

INSERT INTO Issues VALUES
(1, 2, 1, '2020-03-12', '2020-03-18'),
(2, 1, 2, '2020-03-14', '2020-03-20'),
(3, 4, 3, '2020-03-25', '2020-04-05'),
(4, 3, 4, '2020-04-01', '2020-04-06');