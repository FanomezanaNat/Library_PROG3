package Entity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Author extends User{
    public Author(String id, String name, String sex) {
        super(id, name, sex);
    }

}
