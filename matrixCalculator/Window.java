/**
 * 
 */

package matrixCalculator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame{
	
//	HELPING VARIABLES
	public static int center = GridBagConstraints.CENTER;
	public static int top = GridBagConstraints.NORTH;
	public static int bottom = GridBagConstraints.SOUTH;
	public static int left = GridBagConstraints.WEST;
	public static int right = GridBagConstraints.EAST;
	public static int none  = GridBagConstraints.NONE;
	public static int both = GridBagConstraints.BOTH;
	public static int hor = GridBagConstraints.HORIZONTAL;
	public static int ver = GridBagConstraints.VERTICAL;
//	END OF HELPING VARIABLES
	
	/**
	 * Creates a window with a 16 : 9 ratio
	 * 
	 * @param container
	 * @param width
	 * @param title
	 * @param exitOperation
	 */
	public Window(Container container, int width, String title, int exitOperation){
		
		setTitle(title);
		setSize(width, width / 16 * 9);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(exitOperation);
		add(container);
		setVisible(true);
		
	}
	
	/**
	 * Creates a window without defining the size
	 * this window packs everything inside it
	 * 
	 * @param container
	 * @param title
	 * @param exitOperation
	 */
	public Window(Container container, String title, int exitOperation, boolean visible){
		
		setTitle(title);
		setDefaultCloseOperation(exitOperation);
		add(container);
		pack();
		setLocationRelativeTo(null);
		setVisible(visible);
		
	}
	
	/**
	 * Sets the theme of a container in a JFrame object
	 * 
	 * @param component
	 * @param background
	 * @param foreground
	 */
	public static void setTheme(Component component, Color background, Color foreground, Font font){
		
		component.setBackground(background);
		component.setForeground(foreground);
		component.setFont(font);
		
//		check if the component is container
//		change the component's border
		if(component instanceof Container){
			
			if(component instanceof JPanel){
				((JPanel)component).setBorder(BorderFactory.createLineBorder(foreground, 1));
			}
			
			for(int i = 0; i < ((Container)component).getComponentCount(); i++){
				
				Component comp = ((Container)component).getComponent(i);
				
//				uses recursion to add the theme to sub components
				setTheme(comp, background, foreground, font);
				
			}
			
		}
		
	}//END OF SET THEME
	

	/**
	 * Sets the theme of a container in a JFrame object
	 * doesn't add a border to the exception component.
	 * it also makes the user choose whether to put 
	 * borders on every component in the window
	 * 
	 * @param container the container to which the theme will be applied
	 * @param background the background color of the theme
	 * @param foreground the font color of the theme
	 * @param font the font of the theme
	 * @param allBorders determines if the borders should be applied to all components or just JPanels
	 * @param exception this component won't have a border
	 */
	public static void setTheme(Component component, Color background, Color foreground, Font font, boolean allBorders, Component exception){
		
		component.setBackground(background);
		component.setForeground(foreground);
		component.setFont(font);
		
//		check if the component is container
//		change the component's border
		if(component instanceof Container){
			
//			check if theme applies border to all
			if(!allBorders){
				
				if(component instanceof JPanel){
					
//					add border as long as it isn't the exception
					if(component != exception){
						((JPanel)component).setBorder(BorderFactory.createLineBorder(foreground, 1));
					}
					
				}
				
			}else{
				
				if(component instanceof JComponent){
					
//					add border as long as it isn't the exception
					if(component != exception){
						((JComponent)component).setBorder(BorderFactory.createLineBorder(foreground, 1));
					}
					
				}
				
			}
			
			
			for(int i = 0; i < ((Container)component).getComponentCount(); i++){
				
				Component comp = ((Container)component).getComponent(i);
				
//				uses recursion to add the theme to sub components
				setTheme(comp, background, foreground, font, true, exception);
				
			}
			
		}
		
	}//END OF SET THEME
	

	/**
	 * Sets the theme of a container in a JFrame object
	 * it doesn't add border to the components in the
	 * exceptions array
	 * 
	 * @param container
	 * @param background
	 * @param foreground
	 */
	public static void setTheme(Component component, Color background, Color foreground, Font font, LinkedList<Component> exceptions){
		
		component.setBackground(background);
		component.setForeground(foreground);
		component.setFont(font);
		
		boolean notException = true;
		
//		check if the component is container
//		change the component's border
		if(component instanceof Container){
			
			if(component instanceof JPanel){
				
//				check through all exception object in the array
				for(int i = 0; i < exceptions.size(); i++){
					if(component == exceptions.get(i)) notException = false;
				}
				
//				if it's not exception then add border
				if(notException){
					((JPanel)component).setBorder(BorderFactory.createLineBorder(foreground, 1));
				}
				
			}
			
			for(int i = 0; i < ((Container)component).getComponentCount(); i++){
				
				Component comp = ((Container)component).getComponent(i);
				
//				uses recursion to add the theme to sub components
				setTheme(comp, background, foreground, font, exceptions);
				
			}
			
		}
		
	}//END OF SET THEME
	
	
	/**
	 * Adds components to a container with a grid bag layout
	 * 
	 * @param cont container to which components will be added
	 * @param comp the component that will be added
	 * @param x the horizontal position of the object in a GridBagLayout
	 * @param y the vertical position of the object in a GridBagLayout
	 * @param width the horizontal space a component will take in a GridBagLayout
	 * @param height the vertical space a component will take in a GridBagLayout
	 * @param fill determines if a component fills its space
	 * @param anchor determines the position of the component in its space
	 * @param insets the margin of the component
	 */
	public static void addComponent(Container cont, JComponent comp, int x, int y, int width, int height, int fill, int anchor, int insets){
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(insets,insets,insets,insets);
		gbc.weightx = 1;
		gbc.weighty = 1;
		
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		gbc.fill = fill;
		gbc.anchor = anchor;
		
		cont.add(comp, gbc);
		
	}//END OF ADD COMPONENT()
	
}
