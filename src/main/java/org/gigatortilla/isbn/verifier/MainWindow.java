package org.gigatortilla.isbn.verifier;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = -7321417026904326962L;
    private JPanel contentPane;

    MainWindow() {
        setTitle("ISBN Verifier");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(5, 5));
    }

    private class SwitchPanelAction extends AbstractAction {
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
        }
    }
}