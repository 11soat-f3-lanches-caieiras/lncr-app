package br.com.tp.lncr.core.commons.interfaces;

import java.time.LocalDateTime;

//Interface para ordernar classes por status e data de criação
public interface SortedByStatusCreated {
    LocalDateTime get_created();
    String getStatus();
}
