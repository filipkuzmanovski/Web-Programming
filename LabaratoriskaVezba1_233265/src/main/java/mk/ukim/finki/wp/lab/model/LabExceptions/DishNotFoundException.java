package mk.ukim.finki.wp.lab.model.LabExceptions;

public class DishNotFoundException extends RuntimeException{
    public DishNotFoundException() {
        super("Dish was not found!");
    }
}
