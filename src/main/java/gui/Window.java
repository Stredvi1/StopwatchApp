package gui;

import app.StopwatchApp;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


/*
Aplikace po stisku tlačítka START vynuluje displej a začne zobrazovat
        průběžný stopovaný čas. Tlačítko STOP stopky zcela zastaví a nechá zobrazený poslední zobrazený
        čas na displeji. Tlačítko MEZIČAS převezme čas na displeji a přidá ho spolu s pořadovým číslem
        do tabulky zobrazené pod tlačítky a displejem, tj. tabulka bude mít dva sloupce POŘ. ČÍSLO, MEZIČAS.
 */

public class Window extends JFrame {

    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JLabel time = new JLabel();

    private StopwatchApp app = new StopwatchApp(time);

    private String[] header = {"Pozice", "Čas"};
    private DefaultTableModel modelD = new DefaultTableModel(header, 0);
    private JTable timeStampTable = new JTable(modelD);


    private JButton btnStart = new JButton("START");
    private JButton btnStop = new JButton("STOP");
    private JButton btnTimeStamp = new JButton("MEZIČAS");

    public Window(int width, int height) {
        super("Stopwatch");
        setSize(width, height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initGui();
        setVisible(true);
    }

    private void initGui() {
        app.start();

        mainPanel.add(initControlPanel(), BorderLayout.NORTH);
        mainPanel.add(initStopwatchPanel(), BorderLayout.CENTER);
        mainPanel.add(initTimeStampPanel(), BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel initControlPanel() {
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        btnStart.addActionListener(e -> {
            start();
        });

        btnTimeStamp.setEnabled(false);
        btnTimeStamp.addActionListener(e -> {
            timestamp();
        });


        btnStop.setEnabled(false);
        btnStop.addActionListener(e -> {
            stop();
        });

        controlPanel.add(btnStart);
        controlPanel.add(btnTimeStamp);
        controlPanel.add(btnStop);

        return controlPanel;
    }

    private JPanel initStopwatchPanel() {
        JPanel stopwatchPanel = new JPanel();

        time.setFont(time.getFont().deriveFont(Font.BOLD, 40f));
        stopwatchPanel.add(time);

        return stopwatchPanel;
    }

    private JPanel initTimeStampPanel() {
        JPanel timeStampPanel = new JPanel();

        JScrollPane scrollPane = new JScrollPane(timeStampTable);
        scrollPane.setPreferredSize(new Dimension(250, 400));

        app.addActionListener(e -> {
            if (e.getID() == 1 && app.getTimeStampSize() != 0) {
                int num = app.getTimeStampSize();
                Object[] row = {num + ".", app.getTimeStamps().get(num - 1)};
                modelD.addRow(row);
            }
            modelD.fireTableDataChanged();
        });

        timeStampPanel.add(scrollPane);

        return timeStampPanel;
    }


    private void start() {
        btnStart.setEnabled(false);
        btnTimeStamp.setEnabled(true);
        btnStop.setEnabled(true);

        clearTimeStamps();
        app.startTimer();

    }

    private void timestamp() {
        app.addTimeStamp();
        mainPanel.repaint();
        mainPanel.updateUI();
    }

    private void stop() {
        app.end();

        btnStart.setEnabled(true);
        btnTimeStamp.setEnabled(false);
        btnStop.setEnabled(false);
    }

    private void clearTimeStamps() {
        app.clearTimeStamps();
        modelD.setNumRows(0);
        modelD.fireTableDataChanged();
        mainPanel.updateUI();
    }
}
