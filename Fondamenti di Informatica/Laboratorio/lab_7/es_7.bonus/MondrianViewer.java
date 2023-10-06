import javax.swing.JFrame;

public class MondrianViewer {

    public static void main(String[] args) {

        JFrame frame = new JFrame();

        frame.setSize(1024, 768);
        frame.setTitle("Mondrian Art");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BlockComponent component = new BlockComponent();
        frame.add(component);

        frame.setVisible(true);
        System.out.println("Fine");
    }

}
