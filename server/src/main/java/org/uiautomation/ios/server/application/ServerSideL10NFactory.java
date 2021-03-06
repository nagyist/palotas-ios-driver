package org.uiautomation.ios.server.application;

import org.uiautomation.ios.UIAModels.predicate.MatchingStrategy;
import org.uiautomation.ios.UIAModels.predicate.NameCriteria;

public class ServerSideL10NFactory {

  private final ServerSideL10NDecorator decorator;

  public ServerSideL10NFactory(IOSApplication aut) {
    this.decorator = new ServerSideL10NDecorator(aut);
  }

  public NameCriteria nameCriteria(String serverKey) {
    NameCriteria criteria = new NameCriteria(serverKey, MatchingStrategy.serverL10N);
    criteria.addDecorator(decorator);
    criteria.decorate();
    return criteria;
  }
}
