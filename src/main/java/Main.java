import gui.Window;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        /*
        Vytvořte aplikaci stopky, která bude obsahovat tlačítka START, STOP, MEZIČAS a zobrazení času
        na "displeji" ve formátu HH:MM:SS:sss, kde HH jsou hodiny, MM jsou minuty, SS jsou sekundy
        a sss jsou milisekundy. Aplikace po stisku tlačítka START vynuluje displej a začne zobrazovat
        průběžný stopovaný čas. Tlačítko STOP stopky zcela zastaví a nechá zobrazený poslední zobrazený
        čas na displeji. Tlačítko MEZIČAS převezme čas na displeji a přidá ho spolu s pořadovým číslem
        do tabulky zobrazené pod tlačítky a displejem, tj. tabulka bude mít dva sloupce POŘ. ČÍSLO, MEZIČAS.

        Aplikace umožní uložení naměřených mezičasů z tabulky do formátu CSV s údaji pořadové číslo a mezičas.
         */

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window(300, 600).setVisible(true);
            }
        });

    }
}