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

    /**
     * Method to get the Id of a genre
     * @return Integer corresponding of the genre's id
     */
    public int getId() {
        return id;
    }

    /**
     * Method to get the name of the genre
     * @return String corresponding the name of the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Method to get a number corresponding to the number of songs classified inside that genre
     * @return Integer corresponding to the number of songs classified inside that genre
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Method to add 1 to the total amount of songs classified in the corresponding genre
     */
    public void incrementAmount() {
        amount++;
    }

    /**
     * Method to subtract 1 to the total amount of songs classified in the corresponding genre. Never will be under 0.
     */
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
