CREATE TABLE IF NOT EXISTS"Books" (
    id varchar(50) primary key,
    bookName varchar(200),
    pageNumber int,
    idAuthor int references "author"(id),
    topic "Topic"
);
