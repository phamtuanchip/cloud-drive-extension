/*
 * Copyright (C) 2012 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.exoplatform.clouddrive.ecms.filters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;

import org.exoplatform.ecm.webui.component.explorer.UIJCRExplorer;
import org.exoplatform.portal.webui.util.Util;
import org.exoplatform.services.cms.drives.DriveData;
import org.exoplatform.services.cms.drives.impl.ManageDriveServiceImpl;
import org.exoplatform.webui.ext.filter.UIExtensionFilter;
import org.exoplatform.webui.ext.filter.UIExtensionFilterType;

/**
 * Filter for personal drives.
 */
public class PersonalDocumentsFilter implements UIExtensionFilter {

  /**
   * {@inheritDoc}
   */
  public boolean accept(Map<String, Object> context) throws Exception {
    String userId = Util.getPortalRequestContext().getRemoteUser();
    UIJCRExplorer uiExplorer = (UIJCRExplorer) context.get(UIJCRExplorer.class.getName());
    ManageDriveServiceImpl driveService = uiExplorer.getApplicationComponent(ManageDriveServiceImpl.class);
    List<DriveData> driveData = driveService.getPersonalDrives(userId, new ArrayList<String>());
    Node currentNode = (Node) context.get(Node.class.getName());
    for (DriveData drive : driveData) {
      // XXX only show in Personal Doc's root!
      if (uiExplorer.getDriveData().getHomePath().equals(drive.getHomePath())
          && currentNode.getPath().endsWith(uiExplorer.getSession().getUserID() + "/"
              + drive.getName())) {
        return true;
      }
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  public UIExtensionFilterType getType() {
    return UIExtensionFilterType.MANDATORY;
  }

  /**
   * {@inheritDoc}
   */
  public void onDeny(Map<String, Object> context) throws Exception {
  }
}
