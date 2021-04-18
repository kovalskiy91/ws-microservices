package ws.microservices.insurance.queries;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FindOneQuery implements Query {
    private Long id;

    public static FindOneQuery from(Long id) {
        FindOneQuery query = new FindOneQuery();
        query.setId(id);
        return query;
    }
}
