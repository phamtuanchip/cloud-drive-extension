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
package org.exoplatform.clouddrive;


/**
 * General abstraction for an user of cloud drive. It provides integration points between cloud provider and
 * local cloud drive storage. <br>
 * 
 * Created by The eXo Platform SAS.
 * 
 * @author <a href="mailto:pnedonosko@exoplatform.com">Peter Nedonosko</a>
 * @version $Id: CloudUser.java 00000 Sep 7, 2012 pnedonosko $
 */
public abstract class CloudUser {

  protected final String        id;

  protected final String        username;

  protected final String        email;

  protected final CloudProvider provider;
  
  protected final int           hashCode;

  /**
   * {@link CloudUser} constructor.
   * 
   * @param username {@link String}
   * @param email {@link String}
   */
  public CloudUser(String id, String username, String email, CloudProvider provider) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.provider = provider;

    int hc = 1;
    hc = hc * 9 + id.hashCode();
    hc = hc * 17 + username.hashCode();
    hc = hc * 31 + email.hashCode();
    hc = hc * 31 + provider.hashCode();
    this.hashCode = hc;
  }

  /**
   * User name on given cloud drive.
   * 
   * @return String
   */
  public String getUsername() {
    return username;
  }

  /**
   * User email in cloud drive.
   * 
   * @return String
   */
  public String getEmail() {
    return email;
  }

  /**
   * @return the provider
   */
  public CloudProvider getProvider() {
    return provider;
  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof CloudUser) {
      CloudUser other = (CloudUser) obj;
      return this.id.equals(other.id) && this.username.equals(other.username)
          && this.email.equals(other.email) && this.provider.equals(other.provider);
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append(username);
    s.append(' ');
    s.append('(');
    s.append(email);
    s.append(") at ");
    s.append(provider.getName());
    return s.toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return hashCode;
  }

}
