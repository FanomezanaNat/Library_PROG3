package Entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Subscriber extends User{
    public Subscriber(String id, String name, String sex) {
        super(id, name, sex);
    }

}
