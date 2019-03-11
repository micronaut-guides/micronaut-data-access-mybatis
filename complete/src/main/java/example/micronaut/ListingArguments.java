package example.micronaut;

import io.micronaut.http.uri.UriBuilder;

import javax.annotation.Nullable;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ListingArguments {
    @PositiveOrZero
    private Integer offset = 0;

    @Nullable
    @Positive
    private Integer max;

    @Nullable
    @Pattern(regexp = "id|name")
    private String sort;

    @Pattern(regexp = "asc|ASC|desc|DESC")
    @Nullable
    private String order;

    public ListingArguments() {

    }

    public Optional<Integer> getOffset() {
        if (offset == null) {
            return Optional.empty();
        }
        return Optional.of(offset);
    }

    public void setOffset(@Nullable Integer offset) {
        this.offset = offset;
    }

    public Optional<Integer> getMax() {
        if (max == null) {
            return Optional.empty();
        }
        return Optional.of(max);
    }

    public void setMax(@Nullable Integer max) {
        this.max = max;
    }

    public Optional<String> getSort() {
        if (sort == null) {
            return Optional.empty();
        }
        return Optional.of(sort);
    }

    public void setSort(@Nullable String sort) {
        this.sort = sort;
    }

    public Optional<String> getOrder() {
        if (order == null) {
            return Optional.empty();
        }
        return Optional.of(order);
    }

    public void setOrder(@Nullable String order) {
        this.order = order;
    }

    public static Builder builder() {
        return new Builder();
    }

    public URI of(UriBuilder uriBuilder) {
        if (max != null) {
            uriBuilder.queryParam("max", max);
        }
        if (order != null) {
            uriBuilder.queryParam("order", order);
        }
        if (offset != null) {
            uriBuilder.queryParam("offset", offset);
        }
        if (sort != null) {
            uriBuilder.queryParam("sort", sort);
        }
        return uriBuilder.build();
    }

    public static final class Builder {
        private ListingArguments args = new ListingArguments();

        private Builder() {

        }

        public Builder max(int max) {
            args.setMax(max);
            return this;
        }

        public Builder sort(String sort) {
            args.setSort(sort);
            return this;
        }

        public Builder order(String order) {
            args.setOrder(order);
            return this;
        }

        public Builder offset(int offset) {
            args.setOffset(offset);
            return this;
        }

        public ListingArguments build() {
            return this.args;
        }
    }

}
