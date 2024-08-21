import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class todolist{
    private List<task> tasks;
    private int taskId;
    final String filename = "tasks.csv";

    public todolist(){
        this.tasks = new ArrayList<>();
        this.taskId = 1;
    }

    public void addTask(String title, String desciption, LocalDate completion, boolean isComp){
        task work = new task(taskId++, title, desciption, completion, isComp);
        tasks.add(work);
        System.out.println("The task has successfully been placed!");

    }

    public List<task> removeTask(task t) {
        // Check if the task exists in the list using its ID
        task removal = tasks.stream()
                            .filter(task -> task.getId() == t.getId())
                            .findFirst()
                            .orElse(null);
    
        if (removal != null) {
            tasks.remove(removal);
            System.out.println("Task has been successfully removed.");
        } else {
            System.out.println("This task does not currently exist.");
        }
    
        // Return the updated list of tasks
        return tasks;
    }
    public task getTaskById(int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<task> getIncompleteTasks(){
        List<task> pending = new ArrayList<>();
        for (task work: tasks){
            if (work.isComplete()){
                continue;
            }
            else{
                pending.add(work);
            }
        }
        return pending;

    }

    public List<task> getCompleteTasks(){
        List<task> pending = new ArrayList<>();
        for (task work: tasks){
            if (work.isComplete()){
                pending.add(work);
            }
        }
        return pending;

    }

    public List<task> sortTasksByCompletionDate() {
        List<task> sortedTasks = new ArrayList<>(tasks);
        Collections.sort(sortedTasks, new Comparator<task>() {
            @Override
            public int compare(task t1, task t2) {
                return t1.getCompelteionDate().compareTo(t2.getCompelteionDate());
            }
        });
        return sortedTasks;
    }
    

    public void loadData(){
        int count =0;
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){;
            String line;
            while ((line = br.readLine()) != null) {
                String [] values = line.split(",");
                int id = Integer.valueOf(values[0]);
                String title = values[1];
                String desciption = values[2];
                String date = values[3];
                LocalDate completionDate = LocalDate.parse(date);
                boolean isComplete = Boolean.parseBoolean(values[4]);

                task loadedTask = new task(id, title, desciption, completionDate, isComplete);
                tasks.add(loadedTask);

                if (id >= taskId) {
                    taskId = id + 1;
                }
                count++;
                System.out.println("Successfully loaded task (Count:" + count + ")");

            }
        }catch (IOException e){
            System.out.println("Error loading data from "+ filename);
        }
    }
    
     public void saveData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (task work : tasks) {
                bw.write(work.getId() + "," +
                         work.getTitle() + "," +
                         work.getDescription() + "," +
                         work.getCompelteionDate() + "," +
                         work.isComplete());
                bw.newLine();
            }
            System.out.println("Data has been successfully saved.");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
     }


    public List<task> getTasks() {
        return tasks;
    }

    public void setTasks(List<task> tasks) {
        this.tasks = tasks;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "todolist [tasks=" + tasks + ", taskId=" + taskId + ", getIncompleteTasks()=" + getIncompleteTasks()
                + ", getCompleteTasks()=" + getCompleteTasks() + ", getTasks()=" + getTasks() + ", getTaskId()="
                + getTaskId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";
    }

    
}