import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGUI extends JFrame implements ActionListener, ChangeListener {

    private  JTextField textField;
    private JTextArea textArea;
    private JLabel welcomeLabel;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;

    public SimpleGUI() {
        super("Frame title");
        init();
    }

    private void init() {
        // setting up the frame
        setDefaultCloseOperation(JFrame .EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocation(300, 50);

        // create the MenuBar and menu components
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("File");
        JMenuItem menuItem1 = new JMenuItem("Open");
        JMenuItem menuItem2 = new JMenuItem("Save as");
        menu1.add(menuItem1);
        menu1.add(menuItem2);
        JMenu menu2 = new JMenu("Help");
        JMenuItem helpMenuItem1 = new JMenuItem("FAQ");
        JMenuItem helpMenuItem2 = new JMenuItem("About");
        menu2.add(helpMenuItem1);
        menu2.add(helpMenuItem2);

        // add "File" and "Help" menus to the MenuBar
        menuBar.add(menu1);
        menuBar.add(menu2);

        // create the big text area located in the middle
        textArea = new JTextArea();

        // create welcome label
        welcomeLabel = new JLabel("Welcome to my GUI!");
        welcomeLabel.setFont(new Font("Helvetica", Font.BOLD, 20));

        // create slider and adjust settings
        JSlider slider = new JSlider(0, 40, 20);
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        // create a panel for organizing the label and slider
        JPanel sliderPanel = new JPanel();

        // add label and slider, in left-to-right order
        sliderPanel.add(welcomeLabel);
        sliderPanel.add(slider);

        // create the components at the bottom
        JLabel label = new JLabel("Enter Text");
        textField = new JTextField(10); // accepts upto 10 characters
        JButton sendButton = new JButton("Send");
        JButton resetButton = new JButton("Reset");
        JButton openButton = new JButton("Open");


        // create checkboxes
        checkBox1 = new JCheckBox("Yes");
        checkBox1.setBounds(100, 100, 50, 50);
        checkBox2 = new JCheckBox("No", true);
        checkBox2.setBounds(100, 150, 50, 50);

        // create a panel for organizing the components at the bottom
        JPanel panel = new JPanel(); // a "panel" is not visible

        // add bottom components to the panel, in left-to-right order
        panel.add(label);
        panel.add(textField);
        panel.add(sendButton);
        panel.add(resetButton);
        panel.add(openButton);
        panel.add(checkBox1);
        panel.add(checkBox2);

        // creating a third panel to place slider and bottom panels vertically
        // (allows two rows of UI elements to be displayed)
        JPanel combinedPanels = new JPanel();
        combinedPanels.setLayout(new GridLayout(2, 1));
        combinedPanels.add(sliderPanel, BorderLayout.NORTH);
        combinedPanels.add(panel, BorderLayout.SOUTH);

        // add the menu bar to the TOP of the frame, the big white text area
        // to the MIDDLE of the frame, and the "combinedPanels" (which has
        // the label, slider, text box, buttons, and checkboxes) at the BOTTOM
        add(menuBar, BorderLayout.NORTH);
        add(textArea, BorderLayout.CENTER);
        add(combinedPanels, BorderLayout.SOUTH);

        //event handling
        sendButton.addActionListener(this); //button listens to the gui (this) ; when the button is clicked, GUI knows and sends ot button
        resetButton.addActionListener(this);
        openButton.addActionListener(this);
        menuItem1.addActionListener(this);
        menuItem2.addActionListener(this);
        helpMenuItem1.addActionListener(this);
        helpMenuItem2.addActionListener(this);
        slider.addChangeListener(this);


        // display the frame!
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source instanceof JButton) {
            JButton button = (JButton) source;
            String text = button.getText();
            if (text.equals("Send")) {
                welcomeLabel.setText("Send pressed!");
                String textBoxTest = textField.getText();
                textArea.append(textBoxTest);
            } else if (text.equals("Reset")) {
                welcomeLabel.setText("Reset pressed!");
                textArea.setText("");
                textField.setText("");
                checkBox1.setSelected(false);
                checkBox2.setSelected(false);
            } else if (text.equals("Open")) {
                //create a new window
            }
        } else if (source instanceof JMenuItem) {
            JMenuItem item = (JMenuItem) source;
            String text = item.getText();
            textField.setText(text);
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        JSlider slider = (JSlider) source;
        textArea.setText("" + slider.getValue());
    }
}