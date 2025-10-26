-- Create schema
CREATE SCHEMA IF NOT EXISTS virtual_library;

-- Create Author table
CREATE TABLE IF NOT EXISTS virtual_library.author (
    author_id SERIAL PRIMARY KEY,
    biography VARCHAR(1000),
    birth_date DATE,
    name VARCHAR(100) NOT NULL,
    nationality VARCHAR(255)
);

-- Create Book table
CREATE TABLE IF NOT EXISTS virtual_library.book (
    book_id SERIAL PRIMARY KEY,
    isbn VARCHAR(255) NOT NULL UNIQUE,
    number_pages INT,
    publication_year INT,
    publisher VARCHAR(255),
    title VARCHAR(200) NOT NULL,
    author_id INT NOT NULL,
    CONSTRAINT fk_author FOREIGN KEY (author_id)
        REFERENCES virtual_library.author(author_id)
);
