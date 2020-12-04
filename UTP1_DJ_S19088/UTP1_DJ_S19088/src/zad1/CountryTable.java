package zad1;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
//https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
public class CountryTable{ //implements TableModelListener{
    private final List<String[]> countriesList;
    private static final String PATH = "data/flags/",PNG = ".png";
    public CountryTable(String countriesFileName) {
        countriesList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(countriesFileName));
            String line;
            while((line=reader.readLine()) != null){
               countriesList.add(
                  line.split("\\t")
                );
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JTable create() {

        String [] countriesNames = new String[ countriesList.get(0).length+1];
        for (int i = 0; i < countriesNames.length-1; i++) {
            countriesNames[i] = countriesList.get(0)[i];

        }
        countriesNames[countriesNames.length-1] = "Modification";

        Object [][] countriesData = new Object[countriesList.size()-1][countriesNames.length];

        for (int i = 1; i < countriesList.size(); i++) {// iterowanie od 1 bo 0 to nazwy
            String[] tempArr;
            tempArr = countriesList.get(i);

            ImageIcon icon = new ImageIcon(PATH+tempArr[3]+PNG);

            countriesData[i-1] = new Object[]{tempArr[0], tempArr[1], Integer.parseInt(tempArr[2]), icon,LocalDateTime.now() };
        }
        DefaultTableModel model = new DefaultTableModel(countriesData, countriesNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 2){
                    return Integer.class;
                } else if(column == 3){
                    return ImageIcon.class;
                }else{
                    return String.class; // data tez niech bedzie to string
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) { // edytować można tylko liczby reszte nie
                return column == 2;

            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                super.setValueAt(aValue, row, column);
                super.setValueAt(LocalDateTime.now(),row,column+2);
            }
        };
     //  model.addTableModelListener(this); //powoduje exception

        JTable tab = new JTable(model);
        tab.setDefaultRenderer(Integer.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

               Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);


                   int popultion = (Integer) value;

                   if(popultion>20000) {
                       c.setForeground(Color.RED);
                   } else{// else konieczny bez niego wszystko czerwone
                       c.setForeground(Color.BLACK);
                   }


               return c;
            }
        });




        return tab;
    }

  /*  @Override
    public void tableChanged(TableModelEvent e) {
        if(TableModelEvent.UPDATE == e.getType()){
            DefaultTableModel model = (DefaultTableModel) e.getSource();

            int row = e.getFirstRow();

            int mod = model.findColumn("Modification");



            model.setValueAt(LocalDateTime.now(), row,mod);

            model.fireTableCellUpdated(row,mod);
            model.fireTableChanged(e);

        }*//*

    }*/
}

