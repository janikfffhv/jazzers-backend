package at.fhv.jazzers.backend.domain.model.work;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Genre {
    JAZZ("Jazz"),
    ROCK("Rock"),
    METAL("Metal"),
    NIGHTCORE("Nightcore");

    private final String name;

    Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String availableGenres() {
        return Arrays.stream(Genre.values()).map(Genre::getName).collect(Collectors.joining(", "));
    }

    public static boolean isMember(String input) {
        return Arrays.stream(Genre.values()).anyMatch(genre -> genre.getName().equalsIgnoreCase(input));
    }

    public static boolean isNotMember(String input) {
        return !isMember(input);
    }
}