package io.nirahtech;


import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.junit.jupiter.api.Test;

import io.nirahtech.erp.plugins.Plugin;
import io.nirahtech.erp.plugins.projects.ProjectsPlugin;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        Plugin plugin = new ProjectsPlugin();
        System.out.println(plugin.getVersion());
        // Plugin plugin = new ProjectsPlugin();
        // System.out.println(plugin.getVersion());
        JFrame frame = new JFrame("Test");
        frame.setSize(400, 400);
        frame.add(plugin.getView());
        frame.setVisible(true);
        assertTrue( true );
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        Plugin plugin = new ProjectsPlugin();
        JFrame frame = new JFrame("Test");
        frame.setSize(400, 400);
        frame.add(plugin.getView());
        frame.setVisible(true);
    }
}
