/*
 * Copyright (C) 2003-2012 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.clouddrive.jcr;

import javax.jcr.Node;

import org.apache.commons.chain.Context;
import org.exoplatform.clouddrive.CloudDrive;
import org.exoplatform.clouddrive.CloudDriveService;
import org.exoplatform.services.ext.action.InvocationContext;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;


/**
 * Care about ecd:cloudFile nodes removal. Created by The eXo Platform SAS
 * 
 * @author <a href="mailto:pnedonosko@exoplatform.com">Peter Nedonosko</a>
 * @version $Id: CloudFileAction.java 00000 Oct 5, 2012 pnedonosko $
 */
public class RemoveCloudFileAction extends AbstractJCRAction {

  private static Log LOG = ExoLogger.getLogger(RemoveCloudFileAction.class);

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean execute(Context context) throws Exception {
    Node fileNode = (Node) context.get(InvocationContext.CURRENT_ITEM);
    CloudDriveService drives = drives(context);
    CloudDrive localDrive = drives.findDrive(fileNode);
    if (localDrive != null && accept(localDrive)) {
      try {
        start(localDrive);

        // TODO remove file from the cloud drive and set this action on nt:file
        // LOG.error("***** Cloud Drive file removed: " + cloudFile.getPath() + " *****");
        throw new IllegalStateException(localDrive.getUser().getProvider().getName()
            + " file cannot be locally removed: " + fileNode.getPath());
        // return false;
      } finally {
        done();
      }
    }
    return true;
  }

}
