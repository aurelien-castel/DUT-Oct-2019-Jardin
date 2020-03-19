/**
*@author Thomas Guillemot
*/
package controller;

import javax.swing.*;

import view.VueAddNewLegume;

import java.awt.*;
import java.awt.event.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import models.*;

public class ControlAddNewLegume implements ActionListener {
	private JPanel fond;
	private JTextField nom;
	private JComboBox<FamilleLegume> type_enum;
	private VueAddNewLegume view;

	private DefaultComboBoxModel<String> model;
	private List<String> choices = new ArrayList<>();

	private Boolean[] tabSemis;
	private Boolean[] tabRecolte;

	private JComboBox selected;

	public ControlAddNewLegume(VueAddNewLegume view) {

		this.view = view;

		fond = view.getFond();
		nom = view.getNom_text();
		type_enum = view.getType_enum();
		view.getAjouter().addActionListener(this);
/*
		view.getAjouter().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (nom.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Entrez un nom et un type valide !");
				} else {

					tabSemis = prepareToBooleanArray(
							view.getSemis_date1().getItemAt(view.getSemis_date1().getSelectedIndex()),
							view.getSemis_date2().getItemAt(view.getSemis_date2().getSelectedIndex()));

					tabRecolte = prepareToBooleanArray(
							view.getRecolte_date1().getItemAt(view.getRecolte_date1().getSelectedIndex()),
							view.getRecolte_date2().getItemAt(view.getRecolte_date2().getSelectedIndex()));

					int dateSemis = booleanArrayToInt(tabSemis);
					System.out.println("Semis " + dateSemis);

					int dateRecolte = booleanArrayToInt(tabRecolte);
					System.out.println("Recolte " + dateRecolte);

					AbstractLegumeFactory jlbd = LegumeFactoryBD.getLegumeFactoryBD();

					System.out.println(nom.getText() + "__" + (FamilleLegume) type_enum.getSelectedItem() + "__"
							+ dateSemis + "__" + dateRecolte);

					boolean success = false;
					try {

						jlbd.AddLegume(nom.getText(), (FamilleLegume) type_enum.getSelectedItem(), dateSemis,
								dateRecolte);
						success = true;

					} catch (Exception ex) {

						JOptionPane.showMessageDialog(null, "Probleme nom deja utilise");

					}
					if (success) {

						JOptionPane.showMessageDialog(null,
								"Creation du legume " + nom.getText() + " dans la base\nRedemarrez l'application");

					}

				}

			}

		});*/
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == view.getAjouter()) {

			if (nom.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Entrez un nom et un type valide !");
			} else {

				tabSemis = prepareToBooleanArray(
						view.getSemis_date1().getItemAt(view.getSemis_date1().getSelectedIndex()),
						view.getSemis_date2().getItemAt(view.getSemis_date2().getSelectedIndex()));

				tabRecolte = prepareToBooleanArray(
						view.getRecolte_date1().getItemAt(view.getRecolte_date1().getSelectedIndex()),
						view.getRecolte_date2().getItemAt(view.getRecolte_date2().getSelectedIndex()));

				int dateSemis = booleanArrayToInt(tabSemis);
				System.out.println("Semis " + dateSemis);

				int dateRecolte = booleanArrayToInt(tabRecolte);
				System.out.println("Recolte " + dateRecolte);

				AbstractLegumeFactory jlbd = LegumeFactoryBD.getLegumeFactoryBD();

				System.out.println(nom.getText() + "__" + (FamilleLegume) type_enum.getSelectedItem() + "__"
						+ dateSemis + "__" + dateRecolte);

				boolean success = false;
				try {

					jlbd.AddLegume(nom.getText(), (FamilleLegume) type_enum.getSelectedItem(), dateSemis,
							dateRecolte);
					success = true;

				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, "Probleme nom deja utilise");

				}
				if (success) {

					JOptionPane.showMessageDialog(null,
							"Creation du legume " + nom.getText() + " dans la base\nRedemarrez l'application");

				}

			}
			
		}
		
	}

	public Boolean[] prepareToBooleanArray(String d1, String d2) {

		int i1 = Integer.parseInt(d1);
		int i2 = Integer.parseInt(d2);

		System.out.println(d1 + " " + d2);

		Boolean[] BooArray = new Boolean[12];
		Arrays.fill(BooArray, false);

		if (i1 < i2) {

			for (int i = i1 - 1; i < i2; i++) {

				BooArray[i] = true;

			}

		} else {

			for (int i = i1 - 1; i != i2; i++) {

				if (i == BooArray.length) {
					i = 0;
				}

				BooArray[i] = true;

			}

		}

		System.out.println("BooArray " + Arrays.toString(BooArray));
		return BooArray;
	}

	public int booleanArrayToInt(Boolean[] array) {

		int n = 0;
		int l = array.length;
		for (int i = 0; i < l; ++i) {
			n = (n << 1) + (array[i] ? 1 : 0);
		}

		return n;

	}
}
