import javax.swing.*;
import java.awt.*;

public class SunControl {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new SunFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Sun Control");
            frame.setVisible(true);
        });
    }
}


class SunFrame extends JFrame {
    private final SunPanel sunPanel;

    public SunFrame() {
        sunPanel = new SunPanel();
        JPanel buttonPanel = new JPanel(new GridLayout(2,3));

        JButton upButton = new JButton("Up");
        upButton.addActionListener(e -> sunPanel.moveSun(0, -10));
        buttonPanel.add(upButton);

        JButton downButton = new JButton("Down");
        downButton.addActionListener(e -> sunPanel.moveSun(0, 10));
        buttonPanel.add(downButton);

        JButton leftButton = new JButton("Left");
        leftButton.addActionListener(e -> sunPanel.moveSun(-10, 0));
        buttonPanel.add(leftButton);

        JButton rightButton = new JButton("Right");
        rightButton.addActionListener(e -> sunPanel.moveSun(10, 0));
        buttonPanel.add(rightButton);

        JButton dayButton = new JButton("Day");
        dayButton.addActionListener(e -> sunPanel.setDay(true));

        buttonPanel.add(dayButton);

        JButton nightButton = new JButton("Night");
        nightButton.addActionListener(e -> sunPanel.setDay(false));
        buttonPanel.add(nightButton);

        add(sunPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }
}

class SunPanel extends JPanel {
    private int sunX = 175;
    private int sunY = 125;
    private boolean isDay = true;
    public SunPanel() {
        setPreferredSize(new Dimension(400, 300));
    }
    public void moveSun(int dx, int dy) {
        sunX += dx;
        sunY += dy;
        repaint();
    }

    public void setDay(boolean isDay) {
        this.isDay = isDay;
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isDay) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.LIGHT_GRAY);
        }
        g.fillOval(sunX, sunY, 50, 50);
    }
}