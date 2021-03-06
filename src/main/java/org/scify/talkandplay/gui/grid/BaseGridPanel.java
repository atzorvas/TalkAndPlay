/**
* Copyright 2016 SciFY
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.scify.talkandplay.gui.grid;

import org.scify.talkandplay.gui.grid.tiles.TileCreator;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.scify.talkandplay.gui.grid.selectors.ManualTileSelector;
import org.scify.talkandplay.gui.grid.selectors.MouseSelector;
import org.scify.talkandplay.gui.grid.selectors.TileSelector;
import org.scify.talkandplay.gui.helpers.UIConstants;
import org.scify.talkandplay.models.Category;
import org.scify.talkandplay.models.Tile;
import org.scify.talkandplay.models.User;
import org.scify.talkandplay.models.sensors.MouseSensor;
import org.scify.talkandplay.services.UserService;

/**
 * The base panel that holds the grid. All panels that contain a grid inherit
 * from this.
 *
 * @author christina
 */
public class BaseGridPanel extends BasePanel {

    protected Category currentCategory;
    protected UserService userService;
    protected ArrayList<JPanel> panelList;
    protected ArrayList<Tile> tileList;
    protected int empties;
    protected int width, height;

    protected TileCreator tileCreator;

    public BaseGridPanel(User user, GridFrame parent) {
        super(user, parent);

        if (user.getConfiguration().getSelectionSensor() instanceof MouseSensor) {
            this.selector = new MouseSelector(panelList, user.getConfiguration().getRotationSpeed() * 1000, user.getConfiguration().getRotationSpeed() * 1000);
        } else if (user.getConfiguration().getNavigationSensor() != null) {
            this.selector = new ManualTileSelector(user, panelList, user.getConfiguration().getRotationSpeed() * 1000, user.getConfiguration().getRotationSpeed() * 1000);
        } else {
            this.selector = new TileSelector(panelList, user.getConfiguration().getRotationSpeed() * 1000, user.getConfiguration().getRotationSpeed() * 1000);
        }

        if (currentCategory == null || currentCategory.getRows() == null || currentCategory.getColumns() == null) {
            this.tileCreator = new TileCreator(user, user.getConfiguration().getDefaultGridRow(), user.getConfiguration().getDefaultGridColumn());
        } else {
            this.tileCreator = new TileCreator(user, currentCategory.getRows(), currentCategory.getColumns());
        }

        this.tileList = new ArrayList();
        initComponents();
        initListeners();
        setEmpties();
        setBackground(Color.white);
    }

    private void initListeners() {
        parent.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                selector.cancel();
                tileCreator.freePlayerResources();
                e.getWindow().dispose();
            }
        });
    }

    protected GridBagConstraints c;

    protected void initLayout() {
        setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.weightx = 20;
        c.weighty = 20;
        c.gridx = 0;
        c.gridy = 0;
    }

    protected void setEmpties() {
        if (currentCategory == null || currentCategory.getRows() == null || currentCategory.getColumns() == null) {
            empties = UIConstants.getInstance().getRows() * UIConstants.getInstance().getColumns();
        } else {
            empties = currentCategory.getRows() * currentCategory.getColumns();
        }
    }

    protected void fillWithEmpties() {
        setEmpties();
        empties = empties - panelList.size();
        int rows, columns;

        if (currentCategory == null || currentCategory.getRows() == null || currentCategory.getColumns() == null) {
            rows = UIConstants.getInstance().getRows();
            columns = UIConstants.getInstance().getColumns();
        } else {
            rows = currentCategory.getRows();
            columns = currentCategory.getColumns();
        }

        for (int i = 0; i < empties; i++) {
            add(tileCreator.createEmpty(), c);
            if (c.gridx == (columns - 1)) {
                c.gridx = 0;
                c.gridy++;
            } else {
                c.gridx++;
            }
        }
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
