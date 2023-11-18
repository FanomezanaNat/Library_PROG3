package Entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Subscriber extends User{
    private String id ;
    private String name;
    private char sex;
}
