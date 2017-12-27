package kata6.controller;

import kata6.controller.Attribute;
import kata6.model.Histogram;
import kata6.model.Mail;
import kata6.view.MailHistogramBuilder;
import kata6.view.HistogramDisplay;
import kata6.view.MailListReaderDDBB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import kata6.model.Person;
import kata6.view.DataBaseList;

public class Kata6 {
    private Attribute attribute;
    String nameFile;
    private List<Mail> fileList;
    private MailHistogramBuilder<Mail> builder;
    private Histogram<String> domains;
    private Histogram<Character> letters; 
    private HistogramDisplay histoDisplay;
    private List<Person> people;
    private MailHistogramBuilder<Person> builderPerson; 
    private Histogram<Character> genders;
    private Histogram<Float> weights;
   
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException{
      Kata6 kata6=new Kata6();
      kata6.execute();
    }

    void execute() throws IOException, ClassNotFoundException, SQLException {
        input();
        process();
        output();
    }
    
    void input() throws ClassNotFoundException, SQLException, IOException {
        nameFile = ".\\src\\kata6\\emailsfile.txt";
        
        fileList = MailListReaderDDBB.read(nameFile);
        people = DataBaseList.read();
    }
    
    void process() {
        builder = new MailHistogramBuilder(fileList);
        builderPerson = new MailHistogramBuilder(people);
        domains = builder.buil(new Attribute<Mail, String>() {
            @Override
            public String get(Mail item) {
                return item.getMail().split("@")[1];
            }
        });
        
        letters = builder.buil(new Attribute<Mail,
        Character>() {
            @Override
            public Character get(Mail item) {
                return item.getMail().charAt(0);
            }
        });
        
        genders = builderPerson.buil(new Attribute<Person, Character>(){
            @Override
            public Character get(Person item) {
                return item.getGender();
            }
        });
        
        weights = builderPerson.buil(new Attribute<Person, Float>(){
            @Override 
            public Float get(Person item) {
                return item.getWeight();
            }
        });
    }
    
    void output() {
        histoDisplay = new HistogramDisplay(domains,"Dominios");
        histoDisplay.execute();
        histoDisplay = new HistogramDisplay(letters,"Caracteres");
        histoDisplay.execute();
        histoDisplay = new HistogramDisplay(genders,"Géneros");
        histoDisplay.execute();
        histoDisplay = new HistogramDisplay(weights,"Pesos");
        histoDisplay.execute();
    }
    
}