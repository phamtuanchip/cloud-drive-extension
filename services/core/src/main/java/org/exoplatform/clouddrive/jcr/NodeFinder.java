/*
 * Copyright (C) 2003-2013 eXo Platform SAS.
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

import javax.jcr.Item;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

/**
 * An abstraction for a {@link Node} lookup in JCR repository.<br>
 * We need this to decouple WCM's NodeFinder from dependencies of Cloud Drive core. <br>
 * Created by The eXo Platform SAS.
 * 
 * @author <a href="mailto:pnedonosko@exoplatform.com">Peter Nedonosko</a>
 * @version $Id: NodeFinder.java 00000 Feb 26, 2013 pnedonosko $
 */
public interface NodeFinder {

  /**
   * Find JCR item by given path.
   * 
   * @param userSession {@link Session}
   * @param path {@link String}
   * @param symlinkTarget boolean, if {@code true} and found item is a symlink node, return the symlink target
   *          node, if {@code false} just return the item obtained from the session
   * @throws RepositoryException if repository error eccurs
   * @throws PathNotFoundException if item not found by given path
   */
  Item getItem(Session userSession, String path, boolean symlinkTarget) throws PathNotFoundException,
                                                                       RepositoryException;

}
