package br.com.tp.lncr.core.commons.utils;

import br.com.tp.lncr.core.commons.interfaces.SortedByStatusCreated;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatusOrderUtilsTest {
    static class Pedido implements SortedByStatusCreated {
        private final String status;
        private final LocalDateTime created;
        Pedido(String status, LocalDateTime created) {
            this.status = status;
            this.created = created;
        }
        public String getStatus() { return status; }
        public LocalDateTime get_created() { return created; }
    }

    @Test
    void testStatusOrderMap() {
        List<String> statusList = Arrays.asList("A", "B", "C");
        Map<String, Integer> map = StatusOrderUtils.statusOrderMap(statusList);
        assertEquals(1, map.get("A"));
        assertEquals(2, map.get("B"));
        assertEquals(3, map.get("C"));
    }

    @Test
    void testSortByStatusOrder() {
        List<String> statusList = Arrays.asList("Novo", "Em andamento", "Finalizado");
        List<Pedido> pedidos = Arrays.asList(
            new Pedido("Finalizado", LocalDateTime.of(2025, 7, 18, 10, 0)),
            new Pedido("Novo", LocalDateTime.of(2025, 7, 18, 8, 0)),
            new Pedido("Em andamento", LocalDateTime.of(2025, 7, 18, 9, 0)),
            new Pedido(null, LocalDateTime.of(2025, 7, 18, 7, 0)), // deve ser filtrado
            new Pedido("Novo", null) // deve ser filtrado
        );
        List<Pedido> sorted = StatusOrderUtils.sortByStatusOrder(pedidos, statusList);
        assertEquals("Novo", sorted.get(0).getStatus());
        assertEquals("Em andamento", sorted.get(1).getStatus());
        assertEquals("Finalizado", sorted.get(2).getStatus());
        assertEquals(3, sorted.size());
    }
}

