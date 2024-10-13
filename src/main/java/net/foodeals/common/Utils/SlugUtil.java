package net.foodeals.common.Utils;

import net.foodeals.common.contracts.SlugRepository;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class SlugUtil {

    private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String toSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NON_LATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    public static  String makeUniqueSlug(String slug, SlugRepository repository) {
        String uniqueSlug = slug;
        int counter = 1;
        while (repository.existsBySlug(uniqueSlug)) {
            uniqueSlug = slug + "-" + counter++;
        }
        return uniqueSlug;
    }
}

