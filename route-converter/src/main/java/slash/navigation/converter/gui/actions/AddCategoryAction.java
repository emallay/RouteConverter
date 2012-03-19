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

package slash.navigation.converter.gui.actions;

import slash.navigation.catalog.model.CategoryTreeNode;
import slash.navigation.converter.gui.RouteConverter;
import slash.navigation.converter.gui.models.CatalogModel;
import slash.navigation.gui.FrameAction;

import javax.swing.*;
import javax.swing.tree.TreePath;

import static java.text.MessageFormat.format;
import static java.util.Arrays.asList;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;
import static slash.common.io.Transfer.trim;
import static slash.navigation.converter.gui.helper.JTreeHelper.getSelectedCategoryTreeNode;

/**
 * {@link Action} that adds a category to the {@link CatalogModel}.
 *
 * @author Christian Pesch
 */

public class AddCategoryAction extends FrameAction {
    private final JTree tree;

    public AddCategoryAction(JTree tree) {
        this.tree = tree;
    }

    public void run() {
        RouteConverter r = RouteConverter.getInstance();

        final CategoryTreeNode category = getSelectedCategoryTreeNode(tree);
        String name = showInputDialog(r.getFrame(),
                format(RouteConverter.getBundle().getString("add-category-label"), category.getName()),
                r.getFrame().getTitle(), QUESTION_MESSAGE);
        if (trim(name) == null)
            return;

        ((CatalogModel) tree.getModel()).add(asList(category), asList(name));

        // TODO expand the new category
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TreePath treePath = new TreePath(category.getPath()).pathByAddingChild(category.getChildAt(0));
                tree.expandPath(new TreePath(treePath));
                tree.scrollPathToVisible(new TreePath(treePath.getPath()));
            }
        });
    }
}