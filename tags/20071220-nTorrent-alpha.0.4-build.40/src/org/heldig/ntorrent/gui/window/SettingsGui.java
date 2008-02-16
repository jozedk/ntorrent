/**
 *   nTorrent - A GUI client to administer a rtorrent process 
 *   over a network connection.
 *   
 *   Copyright (C) 2007  Kim Eik
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.heldig.ntorrent.gui.window;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.heldig.ntorrent.NTorrent;
import org.heldig.ntorrent.gui.Window;
import org.heldig.ntorrent.language.Language;
import org.heldig.ntorrent.settings.LocalSettings;
import org.heldig.ntorrent.settings.Settings.Description;


public class SettingsGui implements ActionListener {
	Window window = new Window();
	JPanel buttonpanel = new JPanel();
	JPanel contentpanel = new JPanel();
	JButton save = new JButton(Language.Button_save_settings);
	JButton cancel = new JButton(Language.Button_cancel);
	HashMap<Component,Field> link = new HashMap<Component,Field>();
	
	public SettingsGui() {
		initButtons();
		initContent();
		readSettings();
		window.setAlwaysOnTop(true);
		window.setLayout(new BorderLayout());
		window.add(contentpanel, BorderLayout.CENTER);
		window.add(buttonpanel, BorderLayout.SOUTH);
		window.validate();
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
	
	private void initContent() {
		contentpanel.setLayout(new GridLayout(0,1));
	}

	private void initButtons() {
		save.addActionListener(this);
		cancel.addActionListener(this);
		buttonpanel.add(save);
		buttonpanel.add(cancel);
		
	}

	@SuppressWarnings("unchecked")
	private void readSettings() {
		Field[] fields = LocalSettings.class.getFields();
		for(Field field : fields){	
			Class type = field.getType();			
			try {
				Component c = null;
				if(type.equals(String.class)){
					c = new JTextField((String)field.get(NTorrent.settings));
				} else if(type.equals(boolean.class)) {
					c = new JCheckBox();
					((JCheckBox)c).setSelected(field.getBoolean(NTorrent.settings));
				} else if(type.equals(int.class)) {
					c = new JSpinner(new SpinnerNumberModel(field.getInt(NTorrent.settings),null,null,100));
				}else if(type.equals(File.class)){
					final File file = ((File)field.get(NTorrent.settings));
					final JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
					final JButton browse = new JButton(Language.Button_browse_file);
					final JTextField txt = new JTextField(file.getName());
					browse.addActionListener(new ActionListener(){

						
						public void actionPerformed(ActionEvent e) {
							final JFileChooser chooser = new JFileChooser(file);
							if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
								File file = chooser.getSelectedFile();
								panel.setName(file.getAbsolutePath());
								txt.setText(file.getName());
							}
						}
						
					});
					
					panel.setName(file.getAbsolutePath());
					txt.setEditable(false);
					panel.add(txt);
					panel.add(browse);
					c = panel;
				}
				
				for(Annotation a : field.getAnnotations()){
					if(a instanceof Description){
						if(c instanceof JCheckBox)
							((JCheckBox)c).setText(((Description)a).value());
						else
							contentpanel.add(new JLabel(((Description)a).value()));
					}
				}
				
				if(c != null){
					contentpanel.add(c);
					link.put(c, field);
				}
			} catch(Exception x){
				x.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void saveSettings(){
		for(Component c : contentpanel.getComponents()){
			if(link.containsKey(c)){
				Field field = link.get(c);
				Class type = field.getType();
				try {
					if(type.equals(String.class)){
						field.set(NTorrent.settings, ((JTextField)c).getText());
					} else if(type.equals(boolean.class)){
						field.setBoolean(NTorrent.settings, ((JCheckBox)c).isSelected());
					} else if(type.equals(int.class)) {
						field.setInt(NTorrent.settings,((Integer)((JSpinner)c).getValue()));
					} else if(type.equals(File.class)){
						field.set(NTorrent.settings, new File(c.getName()));
					}
					
				} catch (Exception x ){
					x.printStackTrace();
				}					
			}
		}
		try {
			NTorrent.settings.serialize();
		} catch (Exception x) {
			x.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		Language l = Language.getFromString(e.getActionCommand());
		if(l != null)
			switch(l){
				case Button_save_settings:
					saveSettings();
					break;
				case Button_cancel:
					window.dispose();
					break;
			}
	}
}