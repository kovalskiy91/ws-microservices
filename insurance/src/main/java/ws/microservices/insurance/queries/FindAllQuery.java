package ws.microservices.insurance.queries;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FindAllQuery implements Query {
    private int page;
    private int size;
    private String[] sortBy;

    public static FindAllQuery from(int page, int size, String[] sortBy) {
        FindAllQuery query = new FindAllQuery();
        query.page = page;
        query.size = size;
        query.sortBy = sortBy;
        return query;
    }
}
