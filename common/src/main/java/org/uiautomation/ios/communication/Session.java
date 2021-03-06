/*
 * Copyright 2012 ios-driver committers.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.uiautomation.ios.communication;

import java.util.Map;
import java.util.UUID;


// TODO freynaud extract capa from session
public class Session {

  private final String opaqueKey;
  private final Map<String, Object> desiredCapabilities;
  private Map<String, Object> actualCapabilities;

  public static Session createSession(Map<String, Object> desiredCapabilities) {
    String key = UUID.randomUUID().toString();
    Session res = new Session(key, desiredCapabilities);
    return res;
  }

  public Session(String key){
    this.opaqueKey = key;
    this.desiredCapabilities = null;
  }
  
  @SuppressWarnings("unused")
  private Session() {
    throw new IllegalAccessError();
  }


  private Session(String key, Map<String, Object> desiredCapabilities) {
    this.opaqueKey = key;
    this.desiredCapabilities = desiredCapabilities;
  }

  public String getSessionId() {
    return opaqueKey;
  }

  public Map<String, Object> getDesiredCapabilities() {
    return desiredCapabilities;
  }

  public void setActualCapabilities(Map<String, Object> capabilities) {
    this.actualCapabilities = capabilities;
  }

  public Map<String, Object> getActualCapabilities() {
    return actualCapabilities;
  }

  

  


  



}
