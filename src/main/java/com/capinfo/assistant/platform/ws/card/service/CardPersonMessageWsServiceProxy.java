package com.capinfo.assistant.platform.ws.card.service;

public class CardPersonMessageWsServiceProxy implements com.capinfo.assistant.platform.ws.card.service.CardPersonMessageWsService {
  private String _endpoint = null;
  private com.capinfo.assistant.platform.ws.card.service.CardPersonMessageWsService cardPersonMessageWsService = null;
  
  public CardPersonMessageWsServiceProxy() {
    _initCardPersonMessageWsServiceProxy();
  }
  
  public CardPersonMessageWsServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initCardPersonMessageWsServiceProxy();
  }
  
  private void _initCardPersonMessageWsServiceProxy() {
    try {
      cardPersonMessageWsService = (new com.capinfo.assistant.platform.ws.card.service.CardPersonMessageWsServiceServiceLocator()).getCardPersonMessageWsService();
      if (cardPersonMessageWsService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)cardPersonMessageWsService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)cardPersonMessageWsService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (cardPersonMessageWsService != null)
      ((javax.xml.rpc.Stub)cardPersonMessageWsService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.capinfo.assistant.platform.ws.card.service.CardPersonMessageWsService getCardPersonMessageWsService() {
    if (cardPersonMessageWsService == null)
      _initCardPersonMessageWsServiceProxy();
    return cardPersonMessageWsService;
  }
  
  public com.capinfo.assistant.platform.ws.card.model.CardPersonMessageBack getCardPersonMessageByIDNumber(java.lang.String idCard) throws java.rmi.RemoteException{
    if (cardPersonMessageWsService == null)
      _initCardPersonMessageWsServiceProxy();
    return cardPersonMessageWsService.getCardPersonMessageByIDNumber(idCard);
  }
  
  
}