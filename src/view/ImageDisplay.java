package view;

public interface ImageDisplay {
    void display(String name);
    String currentName();
    void on(Shift shift);
    
    interface Shift{
        String left();
        String right();

        public static class Null implements Shift{

            public String left(){
                return "";
            }
            
            public String right(){
                return "";
            }
            
        }
    }
}
