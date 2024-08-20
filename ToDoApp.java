import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ToDoApp{
    public ToDoApp(){
        JFrame frame = new JFrame("Button Example");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);  

        todolist listOfTask = new todolist();

        List<hTask> tasks = listOfTask.getIncompleteTasks();
        


        JButton button = new JButton("Click Me");
        button.setBounds(100, 100, 200, 50);
        


        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button Clicked");
            }
        });


        frame.add(button);


        frame.setVisible(true);
    }
    public static void main(String[] arg){
        new ToDoApp();
    }
}