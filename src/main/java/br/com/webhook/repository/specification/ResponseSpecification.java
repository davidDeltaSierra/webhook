package br.com.webhook.repository.specification;

import br.com.webhook.model.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

import static java.util.Objects.isNull;

public final class ResponseSpecification {
    public static Specification<Response> findAllByStatusCodeIn(List<Integer> code) {
        if (isNull(code) || code.isEmpty()) {
            return null;
        }
        return ((root, cq, cb) -> {
            CriteriaBuilder.In<Integer> in = cb.in(root.get(Response_.statusCode));
            code.forEach(in::value);
            return in;
        });
    }

    public static Specification<Response> findAllByTagsIn(List<String> tags) {
        if (isNull(tags) || tags.isEmpty()) {
            return null;
        }
        return ((root, cq, cb) -> {
            var requestRoot = root.join(Response_.request);
            var tagAssociationRoot = requestRoot.join(Request_.tagAssociations);
            var tagJoin = tagAssociationRoot.join(TagAssociation_.tag);
            var in = cb.in(tagJoin.get(Tag_.name));
            tags.forEach(in::value);
            return cb.and(
                    cb.equal(requestRoot.get(AbstractEntity_.id), tagAssociationRoot.get(TagAssociation_.identifier)),
                    in
            );
        });
    }
}
