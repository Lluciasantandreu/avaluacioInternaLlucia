package proc.avaluacioInterna;

import processing.core.PApplet;

import java.util.Calendar;

public class Semanario {

    // Textos representatius dels mesos
    String[] months = {"Ene","Feb","Mar","Abr","May","Jun", "Jul","Ago","Sep","Oct","Nov","Dic"};

    // Textos representatius dels dies
    String[] days = {"Dom", "Lun","Mar","Mie","Jue","Vie","Sab"};

    // Informació del calendari
    int año, mes, dia;
    int numDiasMes, numDiasPrevMes;
    int diaSemana, primerDia;

    // Data seleccionada
    boolean dateSelected = false;

    int selectedDia =0, selectedMes =0, selectedAño =0;

    // Calendari actual, i del mes anterior
    Calendar cal, cPrev;

    // Botons del setmanari
    BotonDia[] buttons;

    // Dimensions del calendari
    int x, y, w, h;


    // Constructor
    public Semanario(int x, int y, int w, int h){

        this.buttons = new BotonDia[7];

        this.cal = Calendar.getInstance();

        this.año = cal.get(Calendar.YEAR);
        this.mes = cal.get(Calendar.MONTH) + 1;
        this.dia = cal.get(Calendar.DATE);

        System.out.println("AVUI: "+ this.dia+"/"+this.mes+"/"+this.año);

        this.diaSemana = cal.get(Calendar.DAY_OF_WEEK);
        System.out.println("DAY OF WEEK: "+this.diaSemana);

        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        this.primerDia = cal.get(Calendar.DATE);
        System.out.println("1ST DAY OF WEEK: "+this.primerDia);

        this.x = x; this.y = y; this.w = w; this.h = h;
        createCalendar(x, y, w, h);
    }

    // Getters
    public boolean isDateSelected(){
        return this.dateSelected;
    }
    public String getSelectedDate(){
        return this.selectedDia +"/"+ this.selectedMes + "/"+ this.selectedAño;
    }


    // Setters

    public void setCalendar(int d, int m, int y){
        cal.set(Calendar.YEAR, y);
        cal.set(Calendar.MONTH, m);
        cal.set(Calendar.DATE, d);
    }

    public void setPrevCalendar(int d, int m, int y){
        cPrev.set(Calendar.YEAR, y);
        cPrev.set(Calendar.MONTH, m);
        cPrev.set(Calendar.DATE, d);
    }

    public void setSelectedDate(int d, int m, int y){
        this.selectedDia = d;
        this.selectedMes = m;
        this.selectedAño = y;
    }


    public void createCalendar(int x, int y, int w, int h){

        float dayWidth  = w / 7;
        float dayHeight = h;

        int day = this.primerDia;

        for(int d=0; d<7; d++){
            buttons[d] = new BotonDia(x + d*dayWidth, y, dayWidth, dayHeight, day, mes, año);
            day++;
        }
    }


    // Dibuixa el Calendari
    public void display(PApplet p5){
        p5.pushStyle();
        p5.fill(0); p5.textSize(36); p5.textAlign(p5.LEFT);
        p5.text(months[mes-1]+"/"+ año, x, y - 30);
        for(BotonDia b : buttons){
            if(b!=null){
                b.display(p5);
            }
        }

        if(dateSelected){
            String dateText = this.selectedDia +"/"+this.selectedMes +"/"+this.selectedAño;
            p5.fill(0); p5.textSize(24); p5.textAlign(p5.RIGHT);
            p5.text(dateText, x+w, y - 30);
        }
        p5.popStyle();
    }


    // Comprova si pitjam sobre els botons del Calendari
    public  void checkButtons(PApplet p5){
        for(BotonDia b : buttons){
            if((b!=null)&&(b.enabled)&&(b.mouseOver(p5))){
                boolean prevState = b.selected;
                deselectAll();
                b.setSelected(!prevState);
                if(b.selected){
                    dateSelected = true;
                    setSelectedDate(b.dia,b.mes,b.any);
                }
                else {
                    dateSelected = false;
                }
            }
        }
    }

    // Deselecciona tots els botons del Calendari
    public void deselectAll(){
        for(BotonDia b : buttons){
            if(b!=null){
                b.setSelected(false);
            }
        }
    }
}