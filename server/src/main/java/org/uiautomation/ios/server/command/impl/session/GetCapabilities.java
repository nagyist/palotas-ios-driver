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
package org.uiautomation.ios.server.command.impl.session;

import org.json.JSONObject;
import org.uiautomation.ios.communication.WebDriverLikeRequest;
import org.uiautomation.ios.communication.WebDriverLikeResponse;
import org.uiautomation.ios.server.command.BaseCommandHandler;
import org.uiautomation.ios.server.instruments.SessionsManager;

public class GetCapabilities extends BaseCommandHandler {

  public GetCapabilities(SessionsManager instruments, WebDriverLikeRequest request) {
    super(instruments, request);
  }

  public WebDriverLikeResponse handle() throws Exception {

    JSONObject caps = new JSONObject();
    caps.put("simulator", true);
    caps.put("version", "5.1");
    caps.put("type", "iphone");
    JSONObject json = new JSONObject();
    json.put("sessionId", getSessionsManager().getCurrentSessionId());
    json.put("status", 0);
    json.put("value", caps.toString());
    WebDriverLikeResponse r = new WebDriverLikeResponse(json);

    return r;

  }
}
