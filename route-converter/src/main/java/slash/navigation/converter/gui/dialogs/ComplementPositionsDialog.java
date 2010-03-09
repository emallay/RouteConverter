/*
    This file is part of RouteConverter.

    RouteConverter is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    RouteConverter is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with RouteConverter; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

    Copyright (C) 2007 Christian Pesch. All Rights Reserved.
*/

package slash.navigation.converter.gui.dialogs;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import slash.navigation.*;
import slash.navigation.converter.gui.RouteConverter;
import slash.navigation.converter.gui.helper.DialogAction;
import slash.navigation.gui.SimpleDialog;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Dialog for selecting and complementing {@link BaseNavigationPosition}s from the current {@link BaseRoute}.
 *
 * @author Christian Pesch
 */

public class ComplementPositionsDialog extends SimpleDialog {
    private JPanel contentPane;

    private JButton buttonSelectAll;
    private JButton buttonClearSelection;
    private JButton buttonAddCoordinatesToPositions;
    private JButton buttonAddElevationToPositions;
    private JButton buttonAddSpeedToPositions;
    private JButton buttonAddPostalAddressToPositions;
    private JButton buttonAddPopulatedPlaceToPositions;
    private JLabel labelSelection;

    public ComplementPositionsDialog() {
        super(RouteConverter.getInstance().getFrame(), "complement-positions");
        setTitle(RouteConverter.getBundle().getString("complement-positions-title"));
        setContentPane(contentPane);

        buttonSelectAll.addActionListener(new DialogAction(this) {
            public void run() {
                selectAll();
            }
        });

        buttonClearSelection.addActionListener(new DialogAction(this) {
            public void run() {
                clearSelection();
            }
        });

        buttonAddCoordinatesToPositions.addActionListener(new DialogAction(this) {
            public void run() {
                addCoordinatesToPositions();
            }
        });

        buttonAddElevationToPositions.addActionListener(new DialogAction(this) {
            public void run() {
                addElevationToPositions();
            }
        });

        buttonAddSpeedToPositions.addActionListener(new DialogAction(this) {
            public void run() {
                addSpeedToPositions();
            }
        });

        buttonAddPostalAddressToPositions.addActionListener(new DialogAction(this) {
            public void run() {
                addPostalAddressToPositions();
            }
        });

        buttonAddPopulatedPlaceToPositions.addActionListener(new DialogAction(this) {
            public void run() {
                addPopulatedPlaceToPositions();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                close();
            }
        });

        contentPane.registerKeyboardAction(new DialogAction(this) {
            public void run() {
                close();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        RouteConverter r = RouteConverter.getInstance();
        r.getPositionsView().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting())
                    return;
                handlePositionsUpdate();
            }
        });
        r.getPositionsModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                handlePositionsUpdate();
            }
        });

        handlePositionsUpdate();
    }

    private void handlePositionsUpdate() {
        RouteConverter r = RouteConverter.getInstance();
        int selectedRowCount = r.getPositionsView().getSelectedRowCount();
        labelSelection.setText(MessageFormat.format(RouteConverter.getBundle().getString("selected-positions"), selectedRowCount));

        boolean existsSelectedPosition = selectedRowCount > 0;
        buttonAddCoordinatesToPositions.setEnabled(existsSelectedPosition);
        buttonAddPopulatedPlaceToPositions.setEnabled(existsSelectedPosition);
        buttonAddElevationToPositions.setEnabled(existsSelectedPosition);
        buttonAddPostalAddressToPositions.setEnabled(existsSelectedPosition);
        buttonAddSpeedToPositions.setEnabled(existsSelectedPosition);
        buttonClearSelection.setEnabled(existsSelectedPosition);

        boolean notAllPositionsSelected = r.getPositionsView().getRowCount() > selectedRowCount;
        buttonSelectAll.setEnabled(notAllPositionsSelected);
    }

    private void selectAll() {
        RouteConverter.getInstance().selectAll();
        int selectedRowCount = RouteConverter.getInstance().getPositionsView().getSelectedRowCount();
        labelSelection.setText(MessageFormat.format(RouteConverter.getBundle().getString("selected-all-positions"), selectedRowCount));
    }

    private void clearSelection() {
        RouteConverter.getInstance().clearSelection();
        handlePositionsUpdate();
    }

    private void addCoordinatesToPositions() {
        RouteConverter.getInstance().addCoordinatesToPositions();
    }

    private void addElevationToPositions() {
        RouteConverter.getInstance().addElevationToPositions();
    }

    private void addSpeedToPositions() {
        RouteConverter.getInstance().addSpeedToPositions();
    }

    private void addPostalAddressToPositions() {
        RouteConverter.getInstance().addPostalAddressToPositions();
    }

    private void addPopulatedPlaceToPositions() {
        RouteConverter.getInstance().addPopulatedPlaceToPositions();
    }

    private void close() {
        dispose();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(1, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 10), null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 20), null, null, 0, false));
        labelSelection = new JLabel();
        labelSelection.setText("-");
        panel3.add(labelSelection, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        this.$$$loadLabelText$$$(label1, ResourceBundle.getBundle("slash/navigation/converter/gui/RouteConverter").getString("selection"));
        panel3.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonSelectAll = new JButton();
        this.$$$loadButtonText$$$(buttonSelectAll, ResourceBundle.getBundle("slash/navigation/converter/gui/RouteConverter").getString("select-all"));
        panel4.add(buttonSelectAll, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonClearSelection = new JButton();
        this.$$$loadButtonText$$$(buttonClearSelection, ResourceBundle.getBundle("slash/navigation/converter/gui/RouteConverter").getString("clear-selection"));
        panel4.add(buttonClearSelection, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel4.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        this.$$$loadLabelText$$$(label2, ResourceBundle.getBundle("slash/navigation/converter/gui/RouteConverter").getString("complement-complete-with"));
        panel1.add(label2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonAddCoordinatesToPositions = new JButton();
        this.$$$loadButtonText$$$(buttonAddCoordinatesToPositions, ResourceBundle.getBundle("slash/navigation/converter/gui/RouteConverter").getString("complement-coordinates"));
        panel5.add(buttonAddCoordinatesToPositions, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonAddElevationToPositions = new JButton();
        this.$$$loadButtonText$$$(buttonAddElevationToPositions, ResourceBundle.getBundle("slash/navigation/converter/gui/RouteConverter").getString("complement-elevation"));
        panel5.add(buttonAddElevationToPositions, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonAddPostalAddressToPositions = new JButton();
        this.$$$loadButtonText$$$(buttonAddPostalAddressToPositions, ResourceBundle.getBundle("slash/navigation/converter/gui/RouteConverter").getString("complement-postal-address"));
        panel5.add(buttonAddPostalAddressToPositions, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonAddSpeedToPositions = new JButton();
        this.$$$loadButtonText$$$(buttonAddSpeedToPositions, ResourceBundle.getBundle("slash/navigation/converter/gui/RouteConverter").getString("complement-speed"));
        panel5.add(buttonAddSpeedToPositions, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel5.add(spacer2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        buttonAddPopulatedPlaceToPositions = new JButton();
        this.$$$loadButtonText$$$(buttonAddPopulatedPlaceToPositions, ResourceBundle.getBundle("slash/navigation/converter/gui/RouteConverter").getString("complement-populated-place"));
        panel5.add(buttonAddPopulatedPlaceToPositions, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadLabelText$$$(JLabel component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setDisplayedMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadButtonText$$$(AbstractButton component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
