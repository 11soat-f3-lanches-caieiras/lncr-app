package br.com.tp.lanchescaieiras._core.commons.utils;

import br.com.tp.lanchescaieiras._core.commons.interfaces.EnumWithIdDescription;

import java.util.function.Function;

public class EnumUtils {
    public static <E extends Enum<E> & EnumWithIdDescription> E fromId(Class<E> enumClass, int id, RuntimeException exception) {
        for (E e : enumClass.getEnumConstants()) {
            if (e.getId() == id) {
                return e;
            }
        }
        throw exception;
    }

    public static <E extends Enum<E> & EnumWithIdDescription> E fromDescription(Class<E> enumClass, String description, RuntimeException exception) {
        for (E e : enumClass.getEnumConstants()) {
            if (e.getDescription().equalsIgnoreCase(description)) {
                return e;
            }
        }
        throw exception;
    }

    public static <E extends Enum<E> & EnumWithIdDescription> String listOfAllowIds(Class<E> enumClass) {
        StringBuilder sb = new StringBuilder();
        for (E e : enumClass.getEnumConstants()) {
            if (sb.length() > 0) sb.append(",");
            sb.append(e.getId());
        }
        return sb.toString();
    }

    public static <E extends Enum<E> & EnumWithIdDescription> String listOfAllowDescriptions(Class<E> enumClass) {
        StringBuilder sb = new StringBuilder();
        for (E e : enumClass.getEnumConstants()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(e.getDescription());
        }
        return sb.toString();
    }

    public static <E extends Enum<E> & EnumWithIdDescription, X extends RuntimeException> String validateNewStatusRules(Class<E> enumClass, String actualStatus, String newStatus, Boolean forceUpdate, Function<String, X> exceptionSupplier) {
        newStatus = EnumUtils.fromDescription(enumClass, newStatus, exceptionSupplier.apply("Status inválido: " + newStatus)).getDescription();
        if (forceUpdate == true) {
            return newStatus;
        } else {
            Integer actualStatusId = EnumUtils.fromDescription(enumClass, actualStatus, exceptionSupplier.apply("Status inválido: " + actualStatus)).getId();
            Integer newStatusId = EnumUtils.fromDescription(enumClass, newStatus, exceptionSupplier.apply("Status inválido: " + newStatus)).getId();
            if (actualStatusId + 1 == newStatusId) {
                return newStatus;
            } else {
                throw exceptionSupplier.apply("Erro na atualização no status do pedido. Não é permitido atualizar o status de: " + actualStatus + " para: " + newStatus);
            }
        }
    }

}
