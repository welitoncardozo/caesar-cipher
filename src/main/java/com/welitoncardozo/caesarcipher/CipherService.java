package com.welitoncardozo.caesarcipher;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class CipherService {
    private static final List<Character> ALPHABET = List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
    private static final List<Character> ALPHABET_UPPER = List.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
    private static final List<Character> ALPHABET_NUMBER = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
    private static final List<Character> SKIP_CHARACTERS = List.of(' ', '/', '*');
    private static final int CAESAR_CIPHER_KEY = 3;

    public String getEncrypt(final String message) {
        return applyCryptographyRule(message, this::encrypt);
    }

    public String getDecrypt(final String message) {
        return applyCryptographyRule(message, this::decrypt);
    }

    private String applyCryptographyRule(final String message, final Function<Character, Character> function) {
        if (isNull(message)) throw new IllegalArgumentException("Message is invalid");
        return message.chars()
                .mapToObj(character -> (char) character)
                .map(function)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    private char encrypt(final Character character) {
        if (SKIP_CHARACTERS.contains(character)) return character;
        final List<Character> alphabet = getAlphabet(character);
        return alphabet.get(
                (alphabet.indexOf(character) + CAESAR_CIPHER_KEY) % alphabet.size()
        );
    }

    private char decrypt(final Character character) {
        if (SKIP_CHARACTERS.contains(character)) return character;
        final List<Character> alphabet = getAlphabet(character);
        return Optional.of(alphabet.indexOf(character) - CAESAR_CIPHER_KEY)
                .map(index -> index < 0 ? alphabet.size() + index : index)
                .map(index -> alphabet.get(index % alphabet.size()))
                .orElseThrow(() -> new IllegalArgumentException(String.format("Character %s is invalid", character)));
    }

    private List<Character> getAlphabet(final Character character) {
        if (ALPHABET.contains(character)) return ALPHABET;
        if (ALPHABET_UPPER.contains(character)) return ALPHABET_UPPER;
        if (ALPHABET_NUMBER.contains(character)) return ALPHABET_NUMBER;
        throw new IllegalArgumentException(String.format("Character %s is invalid", character));
    }
}
