/**
 * CardPersonMessageWsServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.capinfo.assistant.platform.ws.card.service;

public class CardPersonMessageWsServiceServiceLocator extends org.apache.axis.client.Service implements com.capinfo.assistant.platform.ws.card.service.CardPersonMessageWsServiceService {

    public CardPersonMessageWsServiceServiceLocator() {
    }


    public CardPersonMessageWsServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CardPersonMessageWsServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CardPersonMessageWsService
    private java.lang.String CardPersonMessageWsService_address = "http://yewu.laoling.bjmzj.gov.cn/assistant_platform/services/CardPersonMessageWsService";

    public java.lang.String getCardPersonMessageWsServiceAddress() {
        return CardPersonMessageWsService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CardPersonMessageWsServiceWSDDServiceName = "CardPersonMessageWsService";

    public java.lang.String getCardPersonMessageWsServiceWSDDServiceName() {
        return CardPersonMessageWsServiceWSDDServiceName;
    }

    public void setCardPersonMessageWsServiceWSDDServiceName(java.lang.String name) {
        CardPersonMessageWsServiceWSDDServiceName = name;
    }

    public com.capinfo.assistant.platform.ws.card.service.CardPersonMessageWsService getCardPersonMessageWsService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CardPersonMessageWsService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCardPersonMessageWsService(endpoint);
    }

    public com.capinfo.assistant.platform.ws.card.service.CardPersonMessageWsService getCardPersonMessageWsService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.capinfo.assistant.platform.ws.card.service.CardPersonMessageWsServiceSoapBindingStub _stub = new com.capinfo.assistant.platform.ws.card.service.CardPersonMessageWsServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getCardPersonMessageWsServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCardPersonMessageWsServiceEndpointAddress(java.lang.String address) {
        CardPersonMessageWsService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.capinfo.assistant.platform.ws.card.service.CardPersonMessageWsService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.capinfo.assistant.platform.ws.card.service.CardPersonMessageWsServiceSoapBindingStub _stub = new com.capinfo.assistant.platform.ws.card.service.CardPersonMessageWsServiceSoapBindingStub(new java.net.URL(CardPersonMessageWsService_address), this);
                _stub.setPortName(getCardPersonMessageWsServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("CardPersonMessageWsService".equals(inputPortName)) {
            return getCardPersonMessageWsService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.card.ws.platform.assistant.capinfo.com", "CardPersonMessageWsServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.card.ws.platform.assistant.capinfo.com", "CardPersonMessageWsService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CardPersonMessageWsService".equals(portName)) {
            setCardPersonMessageWsServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
