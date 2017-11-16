package com.neurotechnology.Gui;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import com.neurotechnology.Library.LibraryInfo;
import com.neurotechnology.Library.NativeManager;

public class AboutBox extends JDialog {
	class LibraryInfoModel extends AbstractTableModel {
		private static final long serialVersionUID = 5888723858957392295L;

		private SortedMap<String, LibraryInfo> libraryInfos = new TreeMap<String, LibraryInfo>();
		private String[][] generatedData = null;
		private String[] columnNames = { "Component", "Version", "Is Activated", "Copyright" };
		private boolean beginUpdateCalled = false;

		public int getColumnCount() {
			return columnNames.length;
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public int getRowCount() {
			checkGenerateList();
			return generatedData.length;
		}

		public Object getValueAt(int row, int col) {
			return generatedData[row][col];
		}

		public boolean isCellEditable(int row, int col) {
			return false;
		}

		public void setLibraryInfo(String libraryName, LibraryInfo libraryInfo) {
			libraryInfos.put(libraryName, libraryInfo);
			generatedData = null; // invalidate
			if (!beginUpdateCalled) {
				fireTableDataChanged();
			}
		}

		public void beginUpdate() {
			beginUpdateCalled = true;
		}

		public void endUpdate() {
			if (beginUpdateCalled) {
				fireTableDataChanged();
				beginUpdateCalled = false;
			}
		}

		private void checkGenerateList() {
			if (generatedData == null) {
				regenerateList();
			}
		}

		private void regenerateList() {
			List<String> component = new Vector<String>();
			List<String> version = new Vector<String>();
			List<String> isActivated = new Vector<String>();
			List<String> copyright = new Vector<String>();

			for (LibraryInfo info : libraryInfos.values()) {
				String activated = null;
				String[] activatedParts = info.getActivated().split(",");
				if (activatedParts.length == 1) {
					activated = activatedParts[0];
					activatedParts = new String[0];
				}

				component.add(info.getProduct());
				version.add("" + info.getVersionMajor() + "." + info.getVersionMinor() + "." + info.getVersionBuild()
						+ "." + info.getVersionRevision());
				isActivated.add(activated);
				copyright.add(info.getCopyright());

				for (String activatedPart : activatedParts) {
					String[] subParts = activatedPart.split(":");
					component.add("    " + subParts[0]);
					version.add(null);
					isActivated.add(subParts[1]);
					copyright.add(null);
				}
			}

			generatedData = new String[component.size()][4];
			for (int i = 0; i < component.size(); i++) {
				generatedData[i][0] = component.get(i);
				generatedData[i][1] = version.get(i);
				generatedData[i][2] = isActivated.get(i);
				generatedData[i][3] = copyright.get(i);
			}
		}
	}

	private static final long serialVersionUID = 1L;

	JPanel mainpanel;
	JTable componentTable;
	LibraryInfoModel libraryInfoModel;

	private AboutBox(String title, String copyright) {
		setTitle(title);
		setSize(640, 380);
		setModal(true);
		setResizable(false);
		mainpanel = new JPanel();
		mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.Y_AXIS));
		mainpanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
		JLabel imageLabel = new JLabel(new ImageIcon(getClass().getResource("/img/NeurotecLogo.png")));
		mainpanel.add(imageLabel);
		JLabel titleLabel = new JLabel(title);
		mainpanel.add(titleLabel);
		if (copyright != null && copyright.length() > 0) {
			JLabel cprLabel = new JLabel(copyright);
			mainpanel.add(cprLabel);
		}
		addComponentTable();
		this.setContentPane(mainpanel);
	}

	private void addComponentTable() {
		libraryInfoModel = new LibraryInfoModel();
		componentTable = new JTable(libraryInfoModel);
		JScrollPane scrollPane = new JScrollPane(componentTable);
		//componentTable.setFillsViewportHeight(true);
		componentTable.setRowSelectionAllowed(false);
		componentTable.setColumnSelectionAllowed(false);
		componentTable.setCellSelectionEnabled(false);
		mainpanel.add(scrollPane);
	}

	private void load() {
		String[] libraryNames = { "NCore", "NImages", "NExtractors", "NMatchers", "NTemplates", "NDeviceManager",
				"NLicensing", "NImageProc", "NBiometricStandards", "NBiometricTools" };
		libraryInfoModel.beginUpdate();
		libraryInfoModel.setLibraryInfo("NeurotecJava", NativeManager.getWrapperLibraryInfo());
		for (String libraryName : libraryNames) {
			try {
				NativeManager.checkLoad(libraryName);
				LibraryInfo libraryInfo = LibraryInfo.get(libraryName);
				libraryInfoModel.setLibraryInfo(libraryName, libraryInfo);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		libraryInfoModel.endUpdate();
	}

	public static void show(String title, String copyright) {
		AboutBox about = new AboutBox(title, copyright);
		about.load();
		about.setVisible(true);
	}
}
