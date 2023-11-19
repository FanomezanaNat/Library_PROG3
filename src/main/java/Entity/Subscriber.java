package Entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Subscriber extends User{
    public Subscriber(String id, String name, String sex) {
        super(id, name, sex);
    }
    public String toString() {
        return  "id :'" + getId() + '\'' +
                ",name:'" + getName() + '\'' +
                ", sex:'" + getSex() + '\'';
    }
}
