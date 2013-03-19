/*
 
 Author      : Angela Lau 
 Course      : CS 111B
 Instructor  : Abbas Moghtanei
 Date        : 03/12/13
 Program Name: NumberConverter.java
 Objective   : This applet converts an integer into the corresponding
               number in base two, eight, ten, or sixteen. 
 
*/
 
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
 
public class NumberConverter extends Applet implements ActionListener, ItemListener
{
   TextField tfnumber;
   CheckboxGroup cbg;
   Checkbox cbdecimal, cbbinary, cboctal, cbhex;
   Checkbox selectedCB;
   Label lnum, lselection, lresult;

//*********************************init()*********************************
   public void init()
   {
      Label ltitle = new Label("Number Converter", Label.CENTER);
      ltitle.setFont(new Font("Dialog",Font.BOLD,28));
      Label lfield = new Label("Enter number:", Label.RIGHT);
      lnum = new Label(); lselection = new Label();
      lresult = new Label();
      tfnumber = new TextField(10);
      cbg = new CheckboxGroup();
      cbdecimal = new Checkbox("to decimal", cbg, false);
      cbbinary = new Checkbox("to binary", cbg, false);
      cboctal = new Checkbox("to octal", cbg, false);
      cbhex = new Checkbox("to hexadecimal", cbg, false);
      
      Panel ptextField = new Panel();
      ptextField.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
      ptextField.add(lfield); ptextField.add(tfnumber);
      tfnumber.addActionListener(this);

      Panel pleft = new Panel();
      pleft.setLayout(new GridLayout(4,1));
      pleft.add(ptextField); 
      pleft.add(lnum); pleft.add(lselection); pleft.add(lresult);

      Panel pcheckbox = new Panel();
      pcheckbox.setLayout(new GridLayout(4,1,5,10));
      pcheckbox.add(cbdecimal); pcheckbox.add(cbbinary);
      pcheckbox.add(cboctal);   pcheckbox.add(cbhex);
      cbdecimal.addItemListener(this);
      cbbinary.addItemListener(this);
      cboctal.addItemListener(this);
      cbhex.addItemListener(this);

      Panel pcontainer = new Panel();
      pcontainer.setLayout(new GridLayout(1,2));
      pcontainer.add(pleft); pcontainer.add(pcheckbox);

      setLayout(new BorderLayout(5,20));
      add(pcontainer, BorderLayout.CENTER); 
      add(ltitle, BorderLayout.NORTH);
      setBackground(Color.LIGHT_GRAY);
   }
   
//*******************************getBase()********************************
   public int getBase(Checkbox cb)
   {
      if(cb == cbdecimal)     return(10);
      else if(cb == cbbinary) return(2);
      else if(cb == cboctal)  return(8);
      else                    return(16);
   }
 
//***************************actionPerformed()****************************
   public void actionPerformed(ActionEvent ae) {}

//***************************itemStateChanged()***************************
   public void itemStateChanged(ItemEvent ie)
   {
      Checkbox selectedCB = cbg.getSelectedCheckbox();
      String tfText = tfnumber.getText();
      try
      {
         int num = Integer.parseInt(tfText);
         String value = Integer.toString(num,getBase(selectedCB));
         lselection.setText("converted " + selectedCB.getLabel() +
                            " is");
         lresult.setText(value); 
         lresult.setForeground(Color.BLUE);
      }
      catch(NumberFormatException e)
      {
         lselection.setText("cannot be converted:");
         lresult.setText("not an integer");
         lresult.setForeground(Color.RED);
      }
      finally
      {
         lnum.setText(tfText); 
         lnum.setAlignment(Label.CENTER);
         lselection.setAlignment(Label.CENTER);
         lresult.setAlignment(Label.CENTER);
         lresult.setFont(new Font("Dialog",Font.BOLD,12));
      } 
   }
}
