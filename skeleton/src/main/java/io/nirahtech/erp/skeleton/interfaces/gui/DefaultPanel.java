package io.nirahtech.erp.skeleton.interfaces.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import io.nirahtech.erp.core.Company;
import io.nirahtech.erp.core.Employee;
import io.nirahtech.erp.core.WorkActivity;

public class DefaultPanel extends JPanel {
    private JLabel label = new JLabel("SUPER"); 
    private Company company;

    private final JPanel createHeader() { 
        JPanel header = new JPanel();
        JLabel title = new JLabel("Bonjour !");
        header.add(title);
        return header;
    }
    private final JPanel createMain() { 
        JPanel main = new JPanel(new GridLayout(0, 1));
        if (Objects.isNull(this.company)) {
            JTextField name = new JTextField();
            JTextField creation = new JTextField();
            JTextField address = new JTextField();
            JTextField phoneCountry = new JTextField();
            JTextField phoneNumber = new JTextField();
            JTextField email = new JTextField();
            JTextField siret = new JTextField();
            JTextField siren = new JTextField();
            name.setSize(new Dimension(50, 10));

            Set<Employee> employees = new HashSet<>();
            Set<WorkActivity> projects = new HashSet<>();
        
            JPanel namePanel = new JPanel(new BorderLayout());
            JPanel creationPanel = new JPanel(new BorderLayout());
            JPanel addressPanel = new JPanel(new BorderLayout());
            JPanel phonePanel = new JPanel(new BorderLayout());
            JPanel emailPanel = new JPanel(new BorderLayout());
            JPanel siretPanel = new JPanel(new BorderLayout());
            JPanel sirenPanel = new JPanel(new BorderLayout());

            namePanel.add(new JLabel("Name"), BorderLayout.WEST);
            namePanel.add(name);

            creationPanel.add(new JLabel("Creation"), BorderLayout.WEST);
            creationPanel.add(creation);
            
            addressPanel.add(new JLabel("Address"), BorderLayout.WEST);
            addressPanel.add(address);
            
            phonePanel.add(new JLabel("Phone"), BorderLayout.WEST);
            phonePanel.add(phoneNumber);
            
            emailPanel.add(new JLabel("Email"), BorderLayout.WEST);
            emailPanel.add(email);
            
            siretPanel.add(new JLabel("Siret"), BorderLayout.WEST);
            siretPanel.add(siret);
            
            sirenPanel.add(new JLabel("Siren"), BorderLayout.WEST);
            sirenPanel.add(siren);

            main.add(namePanel);
            main.add(creationPanel);
            main.add(addressPanel);
            main.add(phonePanel);
            main.add(emailPanel);
            main.add(siretPanel);
            main.add(sirenPanel);

            // new Company(
            //     name.getText(),
            //     LocalDate.parse(creation.getText()),
            //     new MailingAddress(address.getText()),
            //     new PhoneNumber(33, Integer.parseInt(phone.getText())),
            //     new EmailAddress(email.getText().split("@")[0], email.getText().split("@")[1]),
            //     siret.getText(),
            //     siren.getText(),
            //     employees,
            //     projects);
        } else {

        }
        return main;
    }
    private final JPanel createFooter() { 
        JPanel footer = new JPanel();
        return footer;
    }

    public DefaultPanel(final Company company) {
        super(new BorderLayout());
        this.add(label);
        this.company = company;
        this.add(this.createHeader(), BorderLayout.NORTH);
        this.add(this.createMain(), BorderLayout.CENTER);
        this.add(this.createFooter(), BorderLayout.SOUTH);

    }
}
