package Entity;

import lombok.*;

import java.sql.Date;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Book {
    private String id;
    private String name;
    private int pageNumber;
    private Date releaseDate;
    private int idAuthor;
    private Topic topic;
    private String status;

    enum Topic{
        Comedy,
        Romance,
        Other
    }
}
