package com.game.HauntedVillage.com.game.HauntedVillage.app;

import java.util.List;

public class Print {
    private String printing;
    private List<String> printList;

    public Print(String printing) {
        setPrinting(printing);
    }

    public Print(List<String> printList) {
        setPrintList(printList);
    }

    public void printToConsole() {
        System.out.println(getPrinting());
    }

    public void printListToConsole() {
        getPrintList().forEach(System.out::println);
    }

    private String getPrinting() {
        return printing;
    }

    private void setPrinting(String printing) {
        this.printing = printing;
    }

    private List<String> getPrintList() {
        return printList;
    }

    private void setPrintList(List<String> printList) {
        this.printList = printList;
    }
}