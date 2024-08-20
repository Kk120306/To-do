import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class todolist{
    private List<task> tasks;
    private int taskId;

    public todolist(){
        this.tasks = new ArrayList<>();
        this.taskId = 1;
    }

    public void addTask(String title, String desciption, LocalDateTime completion, boolean isComp){
        task work = new task(taskId++, title, desciption, completion, isComp);
        tasks.add(work);
        System.out.println("The task has successfully been placed!");

    }

    public void removeTask(int id){
        task removal = tasks.get(id);
        if (removal != null){
            tasks.remove(removal);
            System.out.println("Task has been successfully been removed");
        }
        else{
            System.out.println("This task does not currently exist");
        }
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

    public void sortTasksByCompletionDate() {
        Collections.sort(tasks, new Comparator<task>() {
            @Override 
            public int compare(task t1, task t2) {
                return t1.getCompelteionDate().compareTo(t2.getCompelteionDate());
            }
        });
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