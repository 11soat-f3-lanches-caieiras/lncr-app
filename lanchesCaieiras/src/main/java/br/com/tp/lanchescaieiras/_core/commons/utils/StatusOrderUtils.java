package br.com.tp.lanchescaieiras._core.commons.utils;

import br.com.tp.lanchescaieiras._core.commons.interfaces.SortInterface;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatusOrderUtils {
    public static Map<String, Integer> statusOrderMap(List<String> statusOrderList) {
        Map<String, Integer> statusOrderMap = new HashMap<>();
        for (int i = 0; i < statusOrderList.size(); i++)
            statusOrderMap.put(statusOrderList.get(i), i + 1);
        return statusOrderMap;
    }

    public static <T extends SortInterface, U> List<T> sortByStatusOrder(List<T> list, List<String> statusOrderList) {
        //Defensiva para não ter registros sem informações que são utilizados na ordenação.
        List<T> sortedList = list.stream()
                .filter(order -> order.get_created() != null && order.getStatus() != null)
                .collect(Collectors.toList());
        //Executar ordenação pela sequência de status consultados, estes mais
        sortedList.sort(Comparator
            .comparingInt((SortInterface o) -> StatusOrderUtils.statusOrderMap(statusOrderList).getOrDefault(o.getStatus(), Integer.MAX_VALUE))
            .thenComparing(SortInterface::get_created));
        return sortedList;
    }
}
