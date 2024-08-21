import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class ToDoApp extends JFrame {
    private todolist todoList;
    private JPanel tasksPanel;
    private JTextArea taskDisplayArea;
    

    public ToDoApp() {
        todoList = new todolist(); 
        setTitle("To-Do List");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        taskDisplayArea = new JTextArea();
        taskDisplayArea.setEditable(false);


        // Tasks panel
        tasksPanel = new JPanel();
        tasksPanel.setLayout(new GridLayout(0, 1));  
        JScrollPane scrollPane = new JScrollPane(tasksPanel); 

        // Add task panel 
        JPanel addTaskPanel = new JPanel();
        addTaskPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Input fields and button
        JTextField titleField = new JTextField(10);
        JTextField descriptionField = new JTextField(30);
        JTextField dateField = new JTextField(10);
        JButton addButton = new JButton("Add Task");

        // Add components to addTaskPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        addTaskPanel.add(new JLabel("Title:"), gbc);

        gbc.gridx = 1;
        addTaskPanel.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        addTaskPanel.add(new JLabel("Description:"), gbc);

        gbc.gridx = 1;
        addTaskPanel.add(descriptionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        addTaskPanel.add(new JLabel("Completion Date (YYYY-MM-DD):"), gbc);

        gbc.gridx = 1;
        addTaskPanel.add(dateField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        addTaskPanel.add(addButton, gbc);

        // Title panel 
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridBagLayout());
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), 
                "Welcome To Your Personal To-Do-List", 
                TitledBorder.CENTER, 
                TitledBorder.TOP
        );

        // Set the TitledBorder on the panel
        titlePanel.setBorder(titledBorder);

        // Change the font and size of the title
        Font titleFont = new Font("Arial", Font.BOLD, 18);
        titledBorder.setTitleFont(titleFont);

        // Add Incomplete and Complete buttons to the title panel
        JButton incomplete = new JButton("Sort by Completion Date");
        JButton saveExit = new JButton("Save and Exit");

        gbc.gridx = 0;
        gbc.gridy = 0;
        titlePanel.add(incomplete, gbc);

        gbc.gridx = 1;
        titlePanel.add(saveExit, gbc);


        // Add panels to the frame
        add(scrollPane, BorderLayout.CENTER);
        add(addTaskPanel, BorderLayout.SOUTH);
        add(titlePanel, BorderLayout.NORTH);

        // Show Incomplete button action listener
        incomplete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showIncompleteTasks();
            }
        });

        saveExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                saveData();
            }
            
        });

        // Add task button action listener
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String description = descriptionField.getText();
                String date = dateField.getText();
                LocalDate completionDate = LocalDate.parse(date);
                if (!title.isEmpty() && !description.isEmpty()) {
                    // Add the task to the list
                    todoList.addTask(title, description, completionDate, false);
                    titleField.setText("");
                    descriptionField.setText("");
                    dateField.setText("");
                    refreshTasks();
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter title, description, and date.");
                }
            }
        });

        // Initial display of tasks
        refreshTasks();
    }

    // Method to refresh the tasks display
    private void refreshTasks(List<task> tasks) {
        tasksPanel.removeAll();
        for (task t : tasks) {
            JPanel taskPanel = new JPanel();
            taskPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            taskPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;
    
            JLabel taskLabel = new JLabel(t.getTitle() + " - " + t.getDescription() + " - " + t.getCompelteionDate());
            JButton removeButton = new JButton("Remove");
            JButton completeButton = new JButton("Completed!");
            JButton modifyButton = new JButton("Modify");
    
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    todoList.removeTask(t);  // Remove the task directly by reference
                    refreshTasks(todoList.getTasks()); // Refresh with all tasks
                }
            });

            completeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    t.setComplete(true);  // Mark the task as complete
                    todoList.removeTask(t);
                    refreshTasks(todoList.getTasks()); // Refresh with all tasks
                }
            });
            
            modifyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    showInputPanel(t);
                }
            });
    
            gbc.gridx = 0;
            gbc.gridy = 0;
            taskPanel.add(taskLabel, gbc);
    
            gbc.gridx = 1;
            taskPanel.add(removeButton, gbc);
    
            gbc.gridx = 2;
            taskPanel.add(completeButton, gbc);

            gbc.gridx = 3;
            taskPanel.add(modifyButton, gbc);
    
            tasksPanel.add(taskPanel);
        }
        tasksPanel.revalidate();
        tasksPanel.repaint();
    }

    // Show incomplete tasks method
    private void showIncompleteTasks() {
        List<task> incompleteTasks = todoList.sortTasksByCompletionDate();
        refreshTasks(incompleteTasks);
    }
    private void saveData(){
        todoList.saveData();
        System.exit(0);
    }

    public todolist getTodolist() {
        return todoList;
    }

    private void refreshTasks() {
        refreshTasks(todoList.getTasks());
    }

    private void showInputPanel(task t) {
        // Create a new frame for input
        JFrame inputFrame = new JFrame("Modify Your Task");
        inputFrame.setSize(300, 200);
        inputFrame.setLocationRelativeTo(null);
        
        // Create input fields
        JTextField modTitleField = new JTextField(15);
        JTextField modDescField = new JTextField(15);
        JTextField modDateField = new JTextField(15);

        // Create labels
        JLabel modTitleLabel = new JLabel("Title:");
        JLabel modDescLabel = new JLabel("Description:");
        JLabel modDateLabel = new JLabel("Date: (YYYY-MM-DD)");


        
        // Create a submit button
        JButton submitButton = new JButton("Submit");

        
        // Set up the panel and add components
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(modTitleLabel);
        panel.add(modDescLabel);
        panel.add(modDateLabel);
        panel.add(modTitleField);
        panel.add(modDescField);
        panel.add(modDateField);
        panel.add(new JLabel()); // empty cell
        panel.add(submitButton);
        
        // Add the panel to the frame
        inputFrame.add(panel);
        
        // Make the input frame visible
        inputFrame.setVisible(true);
        
        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Process input data here
                String title = modTitleField.getText();
                String description = modDescField.getText();
                String compDa = modDateField.getText();
                LocalDate completionDate = LocalDate.parse(compDa);
                todoList.getTaskById(t.getId()).editTask(title, description, completionDate);
                modDateField.setText("");
                modDescField.setText("");
                modTitleField.setText("");
                refreshTasks();;
                inputFrame.dispose();
                
            }
        });
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ToDoApp app = new ToDoApp();
                // Load tasks from the CSV file
                app.getTodolist().loadData();
                app.refreshTasks();

                // Show the GUI
                app.setVisible(true);
            }
        });
    }
}
