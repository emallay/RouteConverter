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

package slash.navigation.gui;

import javax.help.CSH;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.*;
import java.util.logging.Logger;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 * Open the {@link HelpSet help}.
 *
 * @author Christian Pesch
 */

public class HelpTopicsAction extends FrameAction {
    private static final Logger log = Logger.getLogger(HelpTopicsAction.class.getName());

    public void run() {
        try {
            HelpBroker broker = Application.getInstance().getContext().getHelpBroker();
            new CSH.DisplayHelpFromFocus(broker).actionPerformed(getEvent());
        } catch (Exception e) {
            String message = "Could not initialize help: " + e.getLocalizedMessage();
            log.severe(message);
            JOptionPane.showMessageDialog(null, message, "Error", ERROR_MESSAGE);
        }
    }
}
