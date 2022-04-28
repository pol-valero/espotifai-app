package business.entities;

public class Genre {

    private int id;
    private String genre;
    private int amount;

    public Genre(int id, String genre, int amount){
        this.id = id;
        this.genre = genre;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
