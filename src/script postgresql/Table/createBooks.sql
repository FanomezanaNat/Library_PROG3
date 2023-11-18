CREATE TABLE IF NOT EXISTS"Books" (
    id varchar(50) primary key,
    bookName varchar(200) not null,
    pageNumber int,
    realeseDate timestamp default now(),
    idAuthor int references "author"(id),
    topic "Topic",
    status varchar(75)
);
