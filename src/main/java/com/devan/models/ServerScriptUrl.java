package com.devan.models;


import java.util.*;

public class ServerScriptUrl {
    public static final Map<String, String> ScriptUrls = createMap();

    private static Map<String, String> createMap() {
        Map<String, String> result = new HashMap<>();
        result.put("cs", "https://raw.githubusercontent.com/sbuggay/linuxgsm/master/CounterStrike/csserver");
        result.put("cscz", "https://github.com/sbuggay/linuxgsm/blob/master/CounterStrikeConditionZero/csczserver");
        result.put("css", "https://raw.githubusercontent.com/sbuggay/linuxgsm/master/CounterStrikeSource/cssserver");
        result.put("csgo", "https://raw.githubusercontent.com/sbuggay/linuxgsm/master/CounterStrikeGlobalOffensive/csgoserver");
        result.put("l4d", "https://raw.githubusercontent.com/sbuggay/linuxgsm/master/Left4Dead/l4dserver");
        result.put("l4d2", "https://raw.githubusercontent.com/sbuggay/linuxgsm/master/Left4Dead2/l4d2server");

        return Collections.unmodifiableMap(result);
    }

    public static List<String> getSupportedGames() {
        return new ArrayList<>(ScriptUrls.keySet());
    }

    public static String getUrlForGame(String game) {
        return ScriptUrls.getOrDefault(game, "https://raw.githubusercontent.com/sbuggay/linuxgsm/master/CounterStrike/csserver");
    }
}
