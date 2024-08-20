
import java.time.LocalDate;

public class task{
    private int id;
    private String title;
    private String description;
    private LocalDate compelteionDate;
    private boolean isComplete;

    public task(int id, String title, String description, LocalDate compelteionDate, boolean isComplete) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.compelteionDate = compelteionDate;
        this.isComplete = isComplete;
    }

    

    public void editTask(String title, String description, LocalDate compelteionDate) {
        this.title = title;
        this.description = description;
        this.compelteionDate = compelteionDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCompelteionDate() {
        return compelteionDate;
    }

    public void setCompelteionDate(LocalDate compelteionDate) {
        this.compelteionDate = compelteionDate;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    @Override
    public String toString() {
        return "task [id=" + id + ", title=" + title + ", description=" + description + ", compelteionDate="
                + compelteionDate + ", isComplete=" + isComplete + "]";
    }


    



}