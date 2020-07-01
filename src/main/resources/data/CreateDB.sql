CREATE TABLE IF NOT EXISTS Publisher
(
    Id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    Name VARCHAR(40) NOT NULL,
    Site VARCHAR(50)
);
CREATE TABLE IF NOT EXISTS Category
(
    Id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    Name VARCHAR(40) NOT NULL,
);
CREATE TABLE IF NOT EXISTS Books
(
    Id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    Title VARCHAR(50) NOT NULL,
    Author VARCHAR(40) NOT NULL,
    Id_category INT NOT NULL,
    Id_publisher INT NOT NULL,
    Price DECIMAL(15, 2) NOT NULL,
    FOREIGN KEY (Id_category) REFERENCES Category (Id) ON DELETE CASCADE,
    FOREIGN KEY (Id_publisher) REFERENCES Publisher (Id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS Member
(
    Id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    Name VARCHAR(40) NOT NULL,
    Surname VARCHAR(40) NOT NULL,
    Password VARCHAR(30) NOT NULL,
    Phone VARCHAR(13) NOT NULL,
    Email VARCHAR(50) NOT NULL,
    City VARCHAR(20) NOT NULL
);
CREATE TABLE IF NOT EXISTS Librarian
(
    Id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    Name VARCHAR(40) NOT NULL,
    Surname VARCHAR(40) NOT NULL,
    Password VARCHAR(30) NOT NULL,
    Phone VARCHAR(13) NOT NULL,
    Email VARCHAR(50) NOT NULL,
    Experience INT NOT NULL
);
CREATE TABLE IF NOT EXISTS Issues
(
    Id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    Id_book INT NOT NULL,
    Id_member INT NOT NULL,
    Id_librarian INT NOT NULL,
    Taken_date DATE NOT NULL,
    Brought_date DATE NOT NULL,
    FOREIGN KEY (Id_member) REFERENCES Member(Id) ON DELETE CASCADE,
    FOREIGN KEY (Id_book) REFERENCES Books(Id) ON DELETE CASCADE,
    FOREIGN KEY (Id_librarian) REFERENCES Librarian(Id) ON DELETE CASCADE
);
