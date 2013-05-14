package com.netflix.jmeter.gui;

import com.netflix.jmeter.sampler.AbstractSampler;
import com.netflix.jmeter.sampler.MultiGetSampler;
import org.apache.jmeter.testelement.TestElement;

import javax.swing.*;
import java.awt.*;

public class MultiGet extends AbstractGUI
{
    private static final long serialVersionUID = 3197090412869386191L;

    public static String LABEL = "Cassandra MultiGet";
    private JTextField CNAME;
    private JComboBox CSERIALIZER;
    private JComboBox VSERIALIZER;

    @Override
    public void configure(TestElement element)
    {
        super.configure(element);
        CNAME.setText(element.getPropertyAsString(MultiGetSampler.COLUMN_NAME));
        CSERIALIZER.setSelectedItem(element.getPropertyAsString(MultiGetSampler.COLUMN_SERIALIZER_TYPE));
        VSERIALIZER.setSelectedItem(element.getPropertyAsString(MultiGetSampler.VALUE_SERIALIZER_TYPE));
    }

    public TestElement createTestElement()
    {
        MultiGetSampler sampler = new MultiGetSampler();
        modifyTestElement(sampler);
        sampler.setComment("test comment");
        return sampler;
    }

    public void modifyTestElement(TestElement sampler)
    {
        super.configureTestElement(sampler);

        if (sampler instanceof MultiGetSampler)
        {
            MultiGetSampler gSampler = (MultiGetSampler) sampler;
            gSampler.setCSerializerType((String) CSERIALIZER.getSelectedItem());
            gSampler.setVSerializerType((String) VSERIALIZER.getSelectedItem());
            gSampler.setColumnName(CNAME.getText());
        }
    }

    public void initFields()
    {
        CNAME.setText("${__Random(1,1000)}");
        CSERIALIZER.setSelectedItem("StringSerializer");
        VSERIALIZER.setSelectedItem("StringSerializer");
    }

    @Override
    public void init(JPanel mainPanel, GridBagConstraints labelConstraints, GridBagConstraints editConstraints)
    {
        addToPanel(mainPanel, labelConstraints, 0, 3, new JLabel("Column Name: ", JLabel.RIGHT));
        addToPanel(mainPanel, editConstraints, 1, 3, CNAME = new JTextField());
        addToPanel(mainPanel, labelConstraints, 0, 4, new JLabel("Column Serializer: ", JLabel.RIGHT));
        addToPanel(mainPanel, editConstraints, 1, 4, CSERIALIZER = new JComboBox(AbstractSampler.getSerializerNames().toArray()));
        addToPanel(mainPanel, labelConstraints, 0, 5, new JLabel("Value Serializer: ", JLabel.RIGHT));
        addToPanel(mainPanel, editConstraints, 1, 5, VSERIALIZER = new JComboBox(AbstractSampler.getSerializerNames().toArray()));
    }

    @Override
    public String getLable()
    {
        return LABEL;
    }
}
