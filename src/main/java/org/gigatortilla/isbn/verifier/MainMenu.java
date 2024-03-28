package org.gigatortilla.isbn.verifier;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMenu extends JPanel {
    MainMenu(JPanel contentPane) {
        setBorder(new EmptyBorder(40, 50, 40, 50));
        setLayout(new GridLayout(2, 1));

        AbstractAction switchToValidationWindow = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                contentPane.setVisible(false);
                if(e.getActionCommand() == "Verify ISBN-10") {
                    contentPane.removeAll();
                    contentPane.add(new ValidationPanel(contentPane, false));
                }
                if(e.getActionCommand() == "Verify ISBN-13") {
                    contentPane.removeAll();
                    contentPane.add(new ValidationPanel(contentPane, true));
                }
                contentPane.setVisible(true);
            }
        };

        JLabel welcomeMessage = new JLabel("Welcome to the ISBN-1X Verifier!", JLabel.CENTER);
        JButton validateButton10 = new JButton("Verify ISBN-10");
        validateButton10.addActionListener(switchToValidationWindow);
        JButton validateButton13 = new JButton("Verify ISBN-13");
        validateButton13.addActionListener(switchToValidationWindow);
        JPanel btnPanel = new JPanel();
        FlowLayout btnPanelLayout = (FlowLayout) btnPanel.getLayout();
        btnPanelLayout.setHgap(20);
        btnPanel.add(validateButton10);
        btnPanel.add(validateButton13);

        add(welcomeMessage);
        add(btnPanel);
    }
}
