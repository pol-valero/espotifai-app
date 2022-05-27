package business.entities;

/**
 * Class that represents genres of songs. Is capable of saving data related to the amount of genres in the app.
 */
public class Genre implements Comparable<Genre>{

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

    public void incrementAmount() {
        amount++;
    }

    public void decrementAmount() {
        if (amount > 0) {
            amount--;
        } else {
            amount = 0;
        }
    }

    @Override
    public int compareTo(Genre o) {
        return Integer.compare(this.amount, o.getAmount());
    }
}
