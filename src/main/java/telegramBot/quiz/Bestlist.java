package telegramBot.quiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bestlist {
    private Map<String, Integer> list = new HashMap<>();

    public Map<String, Integer> getList() {
        return list;
    }

    public void setList(String name, Integer points) {
        Integer currentPoints = list.get(name);

        if (currentPoints == null || points > currentPoints) {
            list.put(name, points);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Bestlist:\n\n");

        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(list.entrySet());
        sortedList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        for (int i = 0; i < sortedList.size(); i++) {
            Map.Entry<String, Integer> entry = sortedList.get(i);
            String playerName = entry.getKey();
            int points = entry.getValue();

            String emoji = "";
            if (i == 0) {
                emoji = "🥇"; // Emoji für den ersten Platz
            } else if (i == 1) {
                emoji = "🥈"; // Emoji für den zweiten Platz
            } else if (i == 2) {
                emoji = "🥉"; // Emoji für den dritten Platz
            } else {
                sb.append(i + 1).append(". "); // Füge die Nummer für Ränge 4 und höher hinzu
            }
            sb.append(emoji).append(playerName).append(": ").append(points).append(" points\n");
        }

        return sb.toString();
    }
}
