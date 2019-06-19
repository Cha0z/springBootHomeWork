package beans.models.domain;

import beans.models.Event;
import beans.models.User;
import lombok.Data;

import java.util.List;


@Data
public class BigTransaction {

    private List<User> users;
    private List<Event> events;

}
