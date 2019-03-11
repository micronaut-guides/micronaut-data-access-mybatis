package example.micronaut.genre;

import example.micronaut.ListingArguments;
import example.micronaut.domain.Genre;
import io.micronaut.validation.Validated;

import javax.inject.Singleton;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Singleton // <1>
@Validated
public class GenreRepositoryImpl implements GenreRepository {

    private final GenreMapper genreMapper;

    public GenreRepositoryImpl(GenreMapper genreMapper) {
        this.genreMapper = genreMapper;
    }

    @Override
    public Optional<Genre> findById(@NotNull Long id) {
        return Optional.ofNullable(genreMapper.findById(id));
    }

    @Override
    public Genre save(@NotBlank String name) {
        Genre genre = new Genre(name);
        genreMapper.save(genre);
        return genre;
    }

    @Override
    public void deleteById(@NotNull Long id) {
        findById(id).ifPresent(genre -> genreMapper.deleteById(id));
    }

    private final static List<String> VALID_PROPERTY_NAMES = Arrays.asList("id", "name");

    public List<Genre> findAll(@NotNull ListingArguments args) {

        if (args.getMax().isPresent() && args.getSort().isPresent() && args.getOffset().isPresent() && args.getSort().isPresent()) {
            return genreMapper.findAllByOffsetAndMaxAndSortAndOrder(args.getOffset().get(),
                    args.getMax().get(),
                    args.getSort().get(),
                    args.getOrder().get());
        }
        if (args.getMax().isPresent() && args.getOffset().isPresent() && (!args.getSort().isPresent() || !args.getOrder().isPresent())) {
            return genreMapper.findAllByOffsetAndMax(args.getOffset().get(),
                    args.getMax().get());
        }
        if ((!args.getMax().isPresent() || !args.getOffset().isPresent()) && args.getSort().isPresent() && args.getOrder().isPresent()) {
            return genreMapper.findAllBySortAndOrder(args.getSort().get(),
                    args.getOrder().get());
        }
        return genreMapper.findAll();
    }

    @Override
    public int update(@NotNull Long id, @NotBlank String name) {
        genreMapper.update(id, name);
        return -1;
    }
}
