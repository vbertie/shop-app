package server.electronics.order.domain;

import java.util.Arrays;

enum Province {

    MAZOWIECKIE, PODLASKIE, WARMINSKO_MAZURSKIE, LUBELSKIE,
    PODKARPACKIE, MALOPOLSKIE, SWIETOKRZYSKIE, LODZKIE,
    SLASKIE, OPOLSKIE, WIELKOPOLSKIE, KUJAWSKO_POMORSKIE,
    POMORSKIE, ZACHODNIO_POMORSKIE, LUBUSKIE, DOLNOSLASKIE;

    static Province toProvince(String givenProvince){
        return Arrays.stream(Province.values())
                .filter(province -> isCorrect(province, givenProvince))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    private static boolean isCorrect(Province province, String givenProvince){
        return province
                .name()
                .equalsIgnoreCase(givenProvince);
    }
}