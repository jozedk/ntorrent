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
package ntorrent.torrenttable.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JTable;

import ntorrent.env.Environment;
import ntorrent.tools.Serializer;
import ntorrent.torrenttable.model.Torrent;
import ntorrent.torrenttable.model.TorrentTableColumnModel;
import ntorrent.torrenttable.model.TorrentTableModel;


public class TorrentTable extends JTable implements MouseListener{
	private static final long serialVersionUID = 1L;
	
	final static TorrentTableColumnModel cmodel = new TorrentTableColumnModel();
	TorrentTableHeaderPopupMenu popup;


	public TorrentTable(TorrentTableModel tmodel) {
		super(tmodel,cmodel);
		getTableHeader().addMouseListener(this);
		this.popup = new TorrentTableHeaderPopupMenu(getColumnModel()); 
		setColumnSelectionAllowed(false);
		setRowHeight(20);
		
		setDoubleBuffered(true);
		
		setDefaultRenderer(Torrent.class, new TorrentClassRenderer());		
	}

	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	
	public void mousePressed(MouseEvent e) {
		if(e.isPopupTrigger()){
			popup.show(this, e.getX(), e.getY());
		}
		
	}

	public void mouseReleased(MouseEvent e) {
		if(e.isPopupTrigger()){
			popup.show(this, e.getX(), e.getY());
		}
		
	}
	

}