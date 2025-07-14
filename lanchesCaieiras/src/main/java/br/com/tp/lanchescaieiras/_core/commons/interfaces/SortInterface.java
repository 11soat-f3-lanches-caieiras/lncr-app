package br.com.tp.lanchescaieiras._core.commons.interfaces;

import java.time.LocalDateTime;

//Interface para ordernar classes por status e data de criação
public interface SortInterface {
    LocalDateTime get_created();
    String getStatus();
}
