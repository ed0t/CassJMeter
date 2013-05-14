package com.netflix.jmeter.gui;

import com.netflix.jmeter.sampler.AbstractSampler;
import com.netflix.jmeter.sampler.MultipleCompositGetSampler;
import org.apache.jmeter.testelement.TestElement;

import javax.swing.*;
import java.awt.*;

public class MultiCompositGet extends AbstractGUI
{
    private static final long serialVersionUID = 3197090412869386191L;
    public static String LABEL = "Cassandra Multi Composite Get";
    private JTextField CNAME;
    private JComboBox VSERIALIZER;

    @Override
    public void configure(TestElement element)
    {
        super.configure(element);
        CNAME.setText(element.getPropertyAsString(MultipleCompositGetSampler.COLUMN_NAME));
        VSERIALIZER.setSelectedItem(element.getPropertyAsString(MultipleCompositGetSampler.VALUE_SERIALIZER_TYPE));
    }

    public TestElement createTestElement()
    {
        MultipleCompositGetSampler sampler = new MultipleCompositGetSampler();
        modifyTestElement(sampler);
        sampler.setComment("test comment");
        return sampler;
    }

    public void modifyTestElement(TestElement sampler)
    {
        super.configureTestElement(sampler);

        if (sampler instanceof MultipleCompositGetSampler)
        {
            MultipleCompositGetSampler gSampler = (MultipleCompositGetSampler) sampler;
            gSampler.setVSerializerType((String) VSERIALIZER.getSelectedItem());
            gSampler.setColumnName(CNAME.getText());
        }
    }

    public void initFields()
    {
        CNAME.setText("${__Random(1,1000)}:${__Random(1,1000)}");
        VSERIALIZER.setSelectedItem("StringSerializer");
    }

    @Override
    public void init(JPanel mainPanel, GridBagConstraints labelConstraints, GridBagConstraints editConstraints)
    {
        addToPanel(mainPanel, labelConstraints, 0, 3, new JLabel("Column Name: ", JLabel.RIGHT));
        addToPanel(mainPanel, editConstraints, 1, 3, CNAME = new JTextField());
        addToPanel(mainPanel, labelConstraints, 0, 5, new JLabel("Value Serializer: ", JLabel.RIGHT));
        addToPanel(mainPanel, editConstraints, 1, 5, VSERIALIZER = new JComboBox(AbstractSampler.getSerializerNames().toArray()));
    }

    @Override
    public String getLable()
    {
        return LABEL;
    }
}
