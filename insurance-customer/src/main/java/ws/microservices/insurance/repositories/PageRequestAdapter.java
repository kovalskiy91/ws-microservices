package ws.microservices.insurance.repositories;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ws.microservices.insurance.queries.FindAllQuery;

public class PageRequestAdapter extends PageRequest {

    private PageRequestAdapter(int page, int size, Sort sort) {
        super(page, size, sort);
    }

    public static PageRequestAdapter from(FindAllQuery query) {
        String[] sortBy = query.getSortBy();
        Sort sort;
        if (sortBy == null || sortBy.length == 0) {
            sort = Sort.unsorted();
        } else {
            sort = Sort.by(sortBy);
        }
        return new PageRequestAdapter(query.getPage(), query.getSize(), sort);
    }

}
