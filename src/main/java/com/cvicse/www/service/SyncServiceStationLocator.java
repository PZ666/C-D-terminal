/**
 * SyncServiceStationLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cvicse.www.service;

public class SyncServiceStationLocator extends org.apache.axis.client.Service implements com.cvicse.www.service.SyncServiceStation {

    public SyncServiceStationLocator() {
    }


    public SyncServiceStationLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SyncServiceStationLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for syncServiceStationPort
    private java.lang.String syncServiceStationPort_address = "http://211.88.20.132:8040/services/syncServiceStation";

    public java.lang.String getsyncServiceStationPortAddress() {
        return syncServiceStationPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String syncServiceStationPortWSDDServiceName = "syncServiceStationPort";

    public java.lang.String getsyncServiceStationPortWSDDServiceName() {
        return syncServiceStationPortWSDDServiceName;
    }

    public void setsyncServiceStationPortWSDDServiceName(java.lang.String name) {
        syncServiceStationPortWSDDServiceName = name;
    }

    public com.cvicse.www.service.SyncServiceStationPortType getsyncServiceStationPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(syncServiceStationPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getsyncServiceStationPort(endpoint);
    }

    public com.cvicse.www.service.SyncServiceStationPortType getsyncServiceStationPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.cvicse.www.service.SyncServiceStationBindingStub _stub = new com.cvicse.www.service.SyncServiceStationBindingStub(portAddress, this);
            _stub.setPortName(getsyncServiceStationPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setsyncServiceStationPortEndpointAddress(java.lang.String address) {
        syncServiceStationPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.cvicse.www.service.SyncServiceStationPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.cvicse.www.service.SyncServiceStationBindingStub _stub = new com.cvicse.www.service.SyncServiceStationBindingStub(new java.net.URL(syncServiceStationPort_address), this);
                _stub.setPortName(getsyncServiceStationPortWSDDServiceName());
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
        if ("syncServiceStationPort".equals(inputPortName)) {
            return getsyncServiceStationPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.cvicse.com/service/", "syncServiceStation");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.cvicse.com/service/", "syncServiceStationPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("syncServiceStationPort".equals(portName)) {
            setsyncServiceStationPortEndpointAddress(address);
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
