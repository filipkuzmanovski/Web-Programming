package mk.ukim.finki.wp.lab.model.LabExceptions;

public class NotSelectedChefException extends RuntimeException{
    public NotSelectedChefException(){
        super("Please select a chef!");
    }
}
